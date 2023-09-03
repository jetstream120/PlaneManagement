package src;

import java.util.*;

/**
 * The RouteGraph class is the graph made of the routes that are in need of
 * being filled.
 */
public class RouteGraph implements IGraph<City, Route>{
    private HashMap<String, City> nameToCityHashMap = new HashMap<>();

    /**
     * Adds a route to the graph by taking in a map from parsing the CSV and
     * creates a new Route using the information from the CSV
     *
     * @param mapIn - The hashmap containing the information from a line in
     *              the CSV.
     */
    @Override
    public void addRoute(Map<String, String> mapIn) {
        String originName = mapIn.get("Origin").toUpperCase();
        String destinationName = mapIn.get("Destination").toUpperCase();
        City origin = this.nameToCityHashMap.getOrDefault(originName,
                new City(originName));
        City destination = this.nameToCityHashMap.getOrDefault(destinationName,
                new City(destinationName));
        Double length = Double.parseDouble(mapIn.get("Length"));
        nameToCityHashMap.put(origin.getName(), origin);
        nameToCityHashMap.put(destination.getName(), destination);
        origin.addOutgoing(new Route(origin, destination, length));
    }

    /**
     * A method used to get all the cities on the Graph
     *
     * @return - A set of all the cities on the graph
     */
    @Override
    public Set<City> getCities() {
        Set<City> returnVal = new HashSet<>();

        for (Map.Entry<String, City> mapCity :
                this.nameToCityHashMap.entrySet()) {
            returnVal.add((City) mapCity.getValue());
        }

        return returnVal;
    }

    /**
     * The getCity method gets the city correlated to the name in the HashMap
     * that is there
     *
     * @param name - The name of the city that is requested
     * @return - The name of the City requested
     */
    @Override
    public City getCity(String name) {
        return this.nameToCityHashMap.get(name.toUpperCase());
    }

    /**
     * Checks if any routes haven't been filled by an aircraft after the
     * aircraft are assigned to routes
     *
     * @return - A list containing all the routes that haven't been filled
     */
    @Override
    public List<Route> checkEdges() {
        List<Route> returnVal = new LinkedList<>();
        for (Map.Entry<String, City> entry : this.nameToCityHashMap.entrySet()) {
            if (!entry.getValue().getEdges().isEmpty()) {
                returnVal.addAll(entry.getValue().getEdges());
            }
        }
        return returnVal;
    }
}
