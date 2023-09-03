package src;

import java.util.ArrayList;
import java.util.Set;

public interface ICity<E> {
    public ArrayList<E> getEdges();
    public void addOutgoing(E edgeIn);
    public String getName();
    public void removeEdge(E edgeIn);
}
