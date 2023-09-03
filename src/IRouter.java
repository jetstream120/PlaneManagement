package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface IRouter<V extends ICity<E>, E extends IEdge<V>> {
    public HashMap<String, List<E>> routeAircraft(IGraph<V, E> graph,
                                                  ArrayList<Plane> planes);
}
