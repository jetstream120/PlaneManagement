package src;

import java.util.ArrayList;

/**
 * The City class which represents a city on the graph and carries data such as
 * the outgoingRoutes.
 */
public class City implements ICity<Route>{
    private String name;
    private ArrayList<Route> outgoingRoutes;

    /**
     * Constructor for the City class
     *
     * @param name - Name of the city
     */
    public City(String name){
        this.name = name;
        this.outgoingRoutes = new ArrayList<>();
    }

    /**
     * Returns the edges (routes) from the city
     *
     * @return - An ArrayList of the edges
     */
    @Override
    public ArrayList<Route> getEdges() {
        return this.outgoingRoutes;
    }

    /**
     * Adds an edge (route) to the city
     *
     * @param edgeIn - Edge that is coming from the graph
     */
    @Override
    public void addOutgoing(Route edgeIn) {
        this.outgoingRoutes.add(edgeIn);
    }

    /**
     * Allows for the name of the city to be returned
     *
     * @return - The name of the city as a String
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Removes the edge (route) from the city
     *
     * @param edgeIn - Edge to remove
     */
    @Override
    public void removeEdge(Route edgeIn) {
        this.outgoingRoutes.remove(edgeIn);
    }
}
