package src;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;

/**
 * This is the controller for the graph and controls functions such as
 * generating and assigning routes.
 */
public class LogisticsController implements IPlaneController<City, Route> {
    private RouteGraph graph;
    private ArrayList<Plane> planes;

    /**
     * The load method takes in 2 file names and converts the CSVs to
     * aircraft and a graph.
     *
     * @param routesFile - name of the CSV file for the routes. Ex:
     *                   "data/routes1.csv"
     * @param aircraftFile - name of the CSV file for the aircraft. Ex:
     *                     "data/planes1.csv"
     * @return - Returns any error messages or a success message
     */
    @Override
    public String load(String routesFile,
                       String aircraftFile) {
        this.graph = new RouteGraph();
        this.planes = new ArrayList<>();
        PlaneCSVParser parser = new PlaneCSVParser();

        Function<Map<String, String>, Void> makeGraph = map -> {
            this.graph.addRoute(map);
            return null;
        };

        Function<Map<String, String>, Void> makePlanes = map -> {
            double range = Double.parseDouble(map.get("Range"));
            double speed = Double.parseDouble(map.get("Speed"));
            String location = map.get("Location").toUpperCase();
            String type = map.get("Type").toUpperCase();
            String registration = map.get("Registration").toUpperCase();
            Plane newPlane = new Plane(type, location,
                    range, speed, registration);
            this.planes.add(newPlane);
            return null;
        };

        try {
            parser.parseData(routesFile, makeGraph);
        } catch (IOException e) {
            return "Error parsing: " + routesFile;
        }

        try {
            parser.parseData(aircraftFile, makePlanes);
        } catch (IOException e) {
            return "Error parsing: " + aircraftFile;
        }

        return "Succesfully loaded all data!";
    }

    /**
     * Finds the routes for the aicraft to fly on
     *
     * @return - Returns a hashmap with the aircraft registration and List
     * for the routes
     */
    @Override
    public HashMap<String, List<Route>> findRoutes() {
        IRouter<City, Route> router = new Router<>();
        return router.routeAircraft(this.graph, this.planes);
    }

    /**
     * Gets routes that are unfilled after the assigning process
     *
     * @return - A list of routes that are unfilled
     */
    @Override
    public List<Route> getUnfilledRoutes() {
        return this.graph.checkEdges();
    }
}
