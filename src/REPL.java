package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class REPL<V,E> {
    private static final String REPL_REGEX = "\"?( |$)(?=(([^\"]*\"){2})*[^\"]*$)\"?";

    private PlaneController<V, E> controller;

    public REPL(PlaneController<V, E> controllerIn) {
        this.controller = controllerIn;
    }

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
                        if (args.length == 3) {
                            String origin = args[1];
                            String destination = args[2];
                            try {
                                List<E> path = this.controller.findRoutes();
                                response = this.getPathString(origin, destination, path);
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

    private String line(String character) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 80; i++) {
            s.append(character);
        }
        return s.toString();
    }

    private String getPathString(String origin, String destination, List<E> path) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.line("=")).append("\n");

        if (path.isEmpty()) {
            sb.append("No route found").append("\n");
        } else {
            sb.append("Origin: ").append(origin.toString());
            sb.append("\nDestination: ").append(destination.toString()).append("\n");
            sb.append(this.line("-")).append("\n");

            for (E leg : path) {
                sb.append(" -- ").append(leg.toString()).append("\n");
            }
        }
        sb.append(this.line("="));
        return sb.toString();
    }
}
