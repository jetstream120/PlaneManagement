package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IGraph<V extends ICity<E>, E extends IEdge<V>> {
    public void addRoute(Map<String,String> mapIn);
    public Set<V> getCities();
    public V getCity(String name);
    public List<E> checkEdges();
}
