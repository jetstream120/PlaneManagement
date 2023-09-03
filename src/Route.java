package src;

/**
 * The Route class contains all the information needed for a route between 2
 * different cities.
 */
public class Route implements IEdge<City> {
    private City origin;
    private City destination;
    private Double length;

    /**
     * Constructor for the Route class that sets values to the instance
     * variables
     *
     * @param start - The starting city of the route
     * @param end - The ending city of the route
     * @param length - The length of the route
     */
    public Route(City start, City end, double length){
        this.origin = start;
        this.destination = end;
        this.length = length;
    }

    /**
     * Gets the source from the route
     *
     * @return - The city where the route starts as a String
     */
    @Override
    public City getSource() {
        return this.origin;
    }

    /**
     * Gets the destination of the route
     *
     * @return - The city where the route ends as a String
     */
    @Override
    public City getDestination() {
        return this.destination;
    }

    /**
     * Gets the length of the route
     *
     * @return - The length of the route as a double
     */
    @Override
    public Double getLength() {
        return this.length;
    }
}
