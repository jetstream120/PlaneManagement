package src;

public class Plane implements IPlane{
    private String type;
    private String location;
    private double range;

    public Plane(String type, String start, double range) {
        this.type = type;
        this.location = start;
        this.range = range;
    }

    @Override
    public double getRange() {
        return this.range;
    }

    @Override
    public String getLocation() {
        return this.location;
    }

    @Override
    public String getType() {
        return this.type;
    }
}
