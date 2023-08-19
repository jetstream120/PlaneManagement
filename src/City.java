package src;

import java.util.HashSet;
import java.util.Set;

public class City implements ICity<Route>{
    private String name;
    private Set<Route> outgoingRoutes;

    public City(String name){
        this.name = name;
        this.outgoingRoutes = new HashSet<>();
    }

    @Override
    public Set<Route> getEdges() {
        return this.outgoingRoutes;
    }

    @Override
    public void addOutgoing(Route edgeIn) {
        this.outgoingRoutes.add(edgeIn);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
