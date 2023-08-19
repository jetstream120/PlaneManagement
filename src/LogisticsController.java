package src;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;

public class LogisticsController implements PlaneController<City, Route> {
    private RouteGraph graph;
    private HashMap<String, ArrayList<Plane>> planeHashMap;

    @Override
    public String load(String routesFile,
                       String aircraftFile) {
        this.graph = new RouteGraph();
        this.planeHashMap = new HashMap<>();
        PlaneCSVParser parser = new PlaneCSVParser();

        Function<Map<String, String>, Void> makeGraph = map -> {
            this.graph.addRoute(map);
            return null;
        };

        Function<Map<String, String>, Void> makePlanes = map -> {
            double range = Double.parseDouble(map.get("Range"));
            String location = map.get("Location").toUpperCase();
            Plane newPlane = new Plane(map.get("Type").toUpperCase(), location,
                    range);
            ArrayList<Plane> val = this.planeHashMap.getOrDefault(location,
                    new ArrayList<>());
            val.add(newPlane);
            this.planeHashMap.put(location, val);
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

    @Override
    public List<Route> findRoutes() {
        return null;
    }
}
