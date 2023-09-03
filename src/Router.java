package src;

import java.util.*;

/**
 * The Router class routes the aircraft to different cities as needed
 *
 * @param <V> - The type of vertice
 * @param <E> - The type of edge
 */
public class Router<V extends ICity<E>, E extends IEdge<V>> implements IRouter<V,E> {
    /**
     * The sortByRange class is a Comparator that compares the range of
     * aircraft and sorts them from greatest to least range.
     */
    class sortByRange implements Comparator<Plane> {
        public int compare(Plane a, Plane b) {
            return (int) b.getRange() - (int) a.getRange();
        }
    }

    /**
     * The sortByDistance class is a Compartor that compares the length of
     * routes and sorts them from longest to shortest
     */
    class sortByDistance implements Comparator<E> {
        public int compare(E a, E b) {
            return (int) (Math.round(b.getLength()) - Math.round(a.getLength()));
        }
    }

    /**
     * The routeAircraft method routes aircraft to routes in the most
     * efficient way possible using a modifed BFS to account for many
     * variables such as range, time, and routes.
     *
     * @param graph - The graph for the routes
     * @param planesIn - The planes that can be used for routes
     * @return - A hashmap mapping Aircraft Registrations to their routes
     * which are in a list.
     */
    @Override
    public HashMap<String, List<E>> routeAircraft(IGraph<V, E> graph,
                                                  ArrayList<Plane> planesIn) {
        planesIn.sort(new sortByRange());
        HashMap<String, List<E>> returnVal = new HashMap<>();
        for (Plane firstPlane : planesIn) {
            HashSet<V> visited = new HashSet<>();
            LinkedList<V> toCheck = new LinkedList<>();
            LinkedList<E> path = new LinkedList<>();
            Double tripTime = 0.0;
            V start = graph.getCity(firstPlane.getLocation());

            visited.add(start);
            toCheck.add(start);

            while (((tripTime < 24.0) && (!toCheck.isEmpty())) && (start != null)) {
                V cityChecked = toCheck.removeFirst();

                ArrayList<E> routes = cityChecked.getEdges();
                routes.sort(new sortByDistance());

                if (!routes.isEmpty()) {
                    for (int i = 0; i < routes.size(); i++) {
                        E route = routes.get(i);
                        if (route.getLength() <= firstPlane.getRange()) {
                            if ((!route.getDestination().getEdges().isEmpty()) && (tripTime > 20.0)) {
                                break;
                            } else {
                                if (!visited.contains(route)) {
                                    visited.add(route.getDestination());
                                    toCheck.addLast(route.getDestination());
                                    path.add(route);
                                    tripTime += route.getLength() / firstPlane.getSpeed();
                                    route.getSource().removeEdge(route);
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            returnVal.put(firstPlane.getRegistration(), backTrack(path));
        }
        return returnVal;
    }

    /**
     * The backTrack method backTracks the route taken by BFS to product a
     * path for an aircraft.
     *
     * @param path - The path generated using BFS
     * @return - The list form of the routes in the right order
     */
    private List<E> backTrack(LinkedList<E> path) {
        LinkedList<E> returnVal = new LinkedList<>();
        if (!path.isEmpty()) {
            returnVal.addAll(path);
        }
        return returnVal;
    }
}
