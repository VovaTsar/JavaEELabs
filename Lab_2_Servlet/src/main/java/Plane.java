/**
 * @author Tsaruk Volodymyr
 * Class Plane for creating object about people who have oskar
 */
public class Plane {
    /**
     * Name of brand
     */
    private String brand;
    /**
     * Captain of plane
     */
    private String captain;
    /**
     * Engine of plane
     */
    private String engine;
    /**
     * Series of plane
     */
    private final String series;

    /**
     * Constructor for creating object about plane
     *
     * @param brand - name of plane
     * @param captain  - captain of plane
     * @param engine  - engine of plane
     * @param series  - series of plane
     */
    public Plane(String brand, String captain, String engine, String series) {
        this.brand = brand;
        this.captain = captain;
        this.engine = engine;
        this.series = series;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "brand='" + brand + '\'' +
                ", captain='" + captain + '\'' +
                ", engine='" + engine + '\'' +
                ", series='" + series + '\'' +
                '}';
    }

    /**
     * Setter of name brand
     *
     * @param brand set name brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Setter of captain of plane
     *
     * @param captain set captain of plane
     */
    public void setCaptain(String captain) {
        this.captain = captain;
    }

    /**
     * Setter of engine
     *
     * @param engine set engine
     */
    public void setEngine(String engine) {
        this.engine = engine;
    }

    /**
     * Getter of brand
     *
     * @return brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Getter of captain
     *
     * @return captain
     */
    public String getCaptain() {
        return captain;
    }

    /**
     * Getter of engine
     *
     * @return engine
     */
    public String getEngine() {
        return engine;
    }

    /**
     * Getter of series
     *
     * @return series
     */
    public String getSeries() {
        return series;
    }
}

