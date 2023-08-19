package src;

import java.util.List;

public interface PlaneController<V, E> {
    public String load(String routesFile,
                       String aircraftFile);

    public List<E> findRoutes();
}
