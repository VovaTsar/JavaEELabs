package JPA;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "plane", schema = "jdbc")
public class PlaneEntity {
    private int id;
    private String brand;
    private String captain;
    private String engine;
    private String series;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    @Column(name = "Captain")
    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    @Basic
    @Column(name = "Engine")
    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    @Basic
    @Column(name = "Series")
    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaneEntity that = (PlaneEntity) o;

        if (id != that.id) return false;
        if (!Objects.equals(brand, that.brand)) return false;
        if (!Objects.equals(captain, that.captain)) return false;
        if (!Objects.equals(engine, that.engine)) return false;
        if (!Objects.equals(series, that.series)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (captain != null ? captain.hashCode() : 0);
        result = 31 * result + (engine != null ? engine.hashCode() : 0);
        result = 31 * result + (series != null ? series.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PlaneEntity{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", captain='" + captain + '\'' +
                ", engine='" + engine + '\'' +
                ", series='" + series + '\'' +
                '}';
    }
}
