package src;

public class Route implements IEdge<City> {
    private City origin;
    private City destination;
    private Double length;

    public Route(City start, City end, double length){
        this.origin = start;
        this.destination = end;
        this.length = length;
    }

    @Override
    public City getSource() {
        return this.origin;
    }

    @Override
    public City getDestination() {
        return this.destination;
    }

    @Override
    public Double getLength() {
        return this.length;
    }
}
