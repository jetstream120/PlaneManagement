package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * The REPL class is the user interface class in the terminal
 *
 * @param <V> - The type of vertice
 * @param <E> - The type of edge
 */
public class REPL<V extends ICity,E extends IEdge> {
    private static final String REPL_REGEX = "\"?( |$)(?=(([^\"]*\"){2})*[^\"]*$)\"?";

    private IPlaneController<V, E> controller;

    /**
     * The constructer for the REPL class
     *
     * @param controllerIn - The PlaneController that is coming in
     */
    public REPL(IPlaneController<V, E> controllerIn) {
        this.controller = controllerIn;
    }

    /**
     * Removes the quotation marks needed when interacting with the REPL
     * interface
     *
     * @param str - String that is turned in
     * @return - String without quotations
     */
    private String stripQuote(String str) {
        int last = str.length() - 1;
        if (last == -1) {
            return str;
        }
        if ((str.charAt(0) == '\'' || str.charAt(0) == '\"')
                && str.charAt(last) == '\'' || str.charAt(last) == '\"') {
            return str.substring(0, last);
        }
        return str;
    }

    /**
     * Runs the REPL from Main.java and interacts with the user in the terminal
     */
    public void run() {
        System.out.print(">>> ");
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            // While loop continuously reads and executes user input during program execution
            while (line != null) {
                // Formats the user input before running commands
                String response = "";
                String[] args = line.split(REPL_REGEX);
                args = Arrays.stream(args).map(this::stripQuote).toArray(String[]::new);
                if (args.length == 0) {
                    continue;
                }
                String command = args[0];
                // Based on the user-inputted commands, calls the corresponding ITravelController method
                switch (command) {
                    case "load":
                        if (args.length == 3) {
                            try {
                                response = this.controller.load(args[1],
                                        args[2]);
                            } catch (Exception e) {
                                response = e.getMessage();
                            }
                        } else {
                            response = "Usage: load [routes_file] " +
                                    "[plane_file]";
                        }
                        break;
                    case "assign":
                        if (args.length == 1) {
                            try {
                                HashMap<String, List<E>> path = this.controller.findRoutes();
                                response = this.getPathString(path);
                            } catch (Exception e) {
                                response = e.getMessage();
                            }
                        } else {
                            response = "Usage: assign";
                        }
                        break;
                    default:
                        response = "Invalid command. Available commands: " +
                                "load, assign";
                }
                System.out.println(response);
                System.out.print(">>> ");
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("IOException occurred.");
        }
    }

    /**
     * Creates a line across the page with the character that is sent in
     *
     * @param character - Character to make a line out of
     * @return - The resulting string that is a line across the console
     */
    private String line(String character) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 80; i++) {
            s.append(character);
        }
        return s.toString();
    }

    /**
     * Takes the path of the aircraft in the form of a hashmap and makes the
     * data readable and usable in the terminal
     *
     * @param path - HashMap containing the path of each aircraft
     * @return - Returns a string with the routing information
     */
    private String getPathString(HashMap<String, List<E>> path) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.line("=")).append("\n");

        if (path.isEmpty()) {
            sb.append("No route found").append("\n");
        } else {
            for (Map.Entry<String, List<E>> entry : path.entrySet()) {
                List<E> visited = entry.getValue();
                sb.append("Registration: ").append(entry.getKey());
                if (entry.getValue().isEmpty()) {
                    sb.append("\nNo route filed for this aircraft. \n");
                } else {
                    sb.append("\nRoute:");
                    int times = 1;
                    for (E route : visited) {
                        sb.append("\nFlight: ").append(times).append("\n");
                        sb.append(route.getSource().getName()).append(
                                "----->").append(route.getDestination().getName())
                                .append("\n");
                        times += 1;
                    }
                }
                sb.append(this.line("=")).append("\n");
            }
        }

        if (!controller.getUnfilledRoutes().isEmpty()) {
            sb.append("The following routes are unfilled due to " +
                    "aircraft shortage or insuffient range:\n");
            for (E route : controller.getUnfilledRoutes()) {
                sb.append(route.getSource().getName()).append(
                                "----->").append(route.getDestination().getName())
                        .append("\n");
            }
        }

        return sb.toString();
    }
}
