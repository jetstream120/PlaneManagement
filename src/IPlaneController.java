package src;

import java.util.HashMap;
import java.util.List;

public interface IPlaneController<V, E> {
    public String load(String routesFile,
                       String aircraftFile);

    public HashMap<String, List<E>> findRoutes();

    public List<E> getUnfilledRoutes();
}
