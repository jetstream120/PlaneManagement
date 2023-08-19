package src;

import java.util.Set;

public interface ICity<E> {
    public Set<E> getEdges();
    public void addOutgoing(E edgeIn);
    public String getName();
}
