package mt.spacewebapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import mt.spacewebapp.models.enums.DestinationType;
import mt.spacewebapp.models.enums.DestinationTypeConverter;

import javax.persistence.*;

@Entity
@Table(name = "Destinations")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String name;
    @Convert(converter = DestinationTypeConverter.class)
    private DestinationType type;
    private long avgOrbitDistance_km;
    private double orbitPeriod_earthYears;
    private double radius_km;
    private int meanTemperature_c;
    private String mainPic;
    @JsonIgnore
    private String factToDisplay;

    public Destination() {
    }


    @Override
    public String toString() {
        return String.format("Destination[%s, %s]", name, type);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DestinationType getType() {
        return type;
    }

    public void setType(DestinationType type) {
        this.type = type;
    }

    public long getAvgOrbitDistance_km() {
        return avgOrbitDistance_km;
    }

    public void setAvgOrbitDistance_km(long avgOrbitDistance_km) {
        this.avgOrbitDistance_km = avgOrbitDistance_km;
    }

    public double getOrbitPeriod_earthYears() {
        return orbitPeriod_earthYears;
    }

    public void setOrbitPeriod_earthYears(double orbitPeriod_earthYears) {
        this.orbitPeriod_earthYears = orbitPeriod_earthYears;
    }

    public double getRadius_km() {
        return radius_km;
    }

    public void setRadius_km(double radius_km) {
        this.radius_km = radius_km;
    }

    public int getMeanTemperature_c() {
        return meanTemperature_c;
    }

    public void setMeanTemperature_c(int meanTemperature_c) {
        this.meanTemperature_c = meanTemperature_c;
    }

    public String getMainPic() {
        return mainPic;
    }

    public void setMainPic(String mainPic) {
        this.mainPic = mainPic;
    }

    public String getFactToDisplay() {
        return factToDisplay;
    }

    public void setFactToDisplay(String factToDisplay) {
        this.factToDisplay = factToDisplay;
    }
}
