package mt.spacewebapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import mt.spacewebapp.models.enums.DestinationType;
import mt.spacewebapp.models.converters.DestinationTypeConverter;

import javax.persistence.*;

@Entity
@Table(name = "Destinations")
@AllArgsConstructor
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String name;
    @Convert(converter = DestinationTypeConverter.class)
    private DestinationType type;
    private double radius_km;
    private long avgOrbitDistance_km;
    private double orbitPeriod_earthYears;

    private int meanTemperature_c;
    private String mainPic;
    @JsonIgnore
    private String factToDisplay;

    public Destination() {
    }


    @Override
    public String toString() {
        return String.format("Destination[%d, %s, %s]", id, name, type);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DestinationType getType() {
        return type;
    }

    public long getAvgOrbitDistance_km() {
        return avgOrbitDistance_km;
    }

    public double getOrbitPeriod_earthYears() {
        return orbitPeriod_earthYears;
    }

    public double getRadius_km() {
        return radius_km;
    }

    public int getMeanTemperature_c() {
        return meanTemperature_c;
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
