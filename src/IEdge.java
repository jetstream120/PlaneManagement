package src;

public interface IEdge<V> {
    public V getSource();
    public V getDestination();
    public Double getLength();
}
