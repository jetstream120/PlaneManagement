package src;

public interface IEdge<V extends ICity> {
    public V getSource();
    public V getDestination();
    public Double getLength();
}
