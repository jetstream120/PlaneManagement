package src;

/**
 * The Plane class holds all the information for aircraft that is needed to
 * generate routes.
 */
public class Plane implements IPlane{
    private String type;
    private String location;
    private double range;
    private double speed;
    private String registration;

    /**
     * The constructor for the Plane class
     *
     * @param type - Type of aircraft
     * @param start - The starting point of the aircraft
     * @param range - The range of the aircraft
     * @param speed - The speed of the aircraft
     * @param registration - The registration of the aircraft
     */
    public Plane(String type, String start, double range, double speed,
                 String registration) {
        this.type = type;
        this.location = start;
        this.range = range;
        this.speed = speed;
        this.registration = registration;
    }

    /**
     * Returns the range of the aircraft
     *
     * @return - The range as a double
     */
    @Override
    public double getRange() {
        return this.range;
    }

    /**
     * The location of the aircraft at the start of the day
     *
     * @return - Location of the aircraft as a String
     */
    @Override
    public String getLocation() {
        return this.location;
    }

    /**
     * Returns the type of aircraft as a string
     *
     * @return - The type of aircraft as String
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * Returns the speed of the aircraft
     *
     * @return - Speed of the aircraft as a Double
     */
    @Override
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Returns the registration of the aircraft
     *
     * @return - Registration of the aircraft as String
     */
    @Override
    public String getRegistration() {
        return this.registration;
    }
}
