package mt.spacewebapp.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TripDto {
    private Integer id;
    private String fromDestinationName;
    private String toDestinationName;
    private LocalDate date;
    private int plannedNumberOfPassengers;
//    private int numberOfTicketsAvailable;
}
