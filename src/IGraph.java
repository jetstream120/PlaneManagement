package src;

import java.util.Map;
import java.util.Set;

public interface IGraph<V extends ICity<E>, E extends IEdge<V>> {
    public void addRoute(Map<String,String> mapIn);
    public Set<V> getCities();
    public V getCity(String name);
    public Set<E> getEdges(V cityFrom);
}
