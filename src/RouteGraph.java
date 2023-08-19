package src;

import java.util.*;

public class RouteGraph implements IGraph<City, Route>{
    private HashMap<String, City> nameToCityHashMap = new HashMap<>();

    @Override
    public void addRoute(Map<String, String> mapIn) {
        String originName = mapIn.get("Origin").toUpperCase();
        String destinationName = mapIn.get("Destination").toUpperCase();
        City origin = this.nameToCityHashMap.getOrDefault(originName,
                new City(originName));
        City destination = this.nameToCityHashMap.getOrDefault(destinationName,
                new City(destinationName));
        nameToCityHashMap.put(origin.getName(), origin);
        nameToCityHashMap.put(destination.getName(), destination);
        origin.addOutgoing(new Route(origin, destination,
                Double.parseDouble(mapIn.get("Length"))));
    }

    @Override
    public Set<City> getCities() {
        Set<City> returnVal = new HashSet<>();

        for (Map.Entry<String, City> mapCity :
                this.nameToCityHashMap.entrySet()) {
            returnVal.add((City) mapCity);
        }

        return returnVal;
    }

    @Override
    public City getCity(String name) {
        return this.nameToCityHashMap.get(name.toUpperCase());
    }

    @Override
    public Set<Route> getEdges(City cityFrom) {
        return cityFrom.getEdges();
    }
}
