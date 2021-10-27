package mt.spacewebapp.dto;

import lombok.Data;
import mt.spacewebapp.models.enums.DestinationType;

@Data
public class DestinationDto {
    private Integer id;
    private String name;
    private DestinationType type;
    private long avgOrbitDistance_km;
    private double orbitPeriod_earthYears;
    private double radius_km;
    private int meanTemperature_c;
    private String mainPic;
    private String factToDisplay;
}
