package mt.spacewebapp.dto;

import lombok.Data;
import mt.spacewebapp.models.enums.TicketClass;
import mt.spacewebapp.models.enums.TicketStatus;

import java.util.UUID;

@Data
public class TicketDto {
    private UUID id;
    private TicketClass ticketClass;
    private TicketStatus ticketStatus;
    private Integer tripId;
    private String tripFromDestinationName;
    private String tripToDestinationName;
    private String tripDate;
    private String customerId;
    private String customerUsername;
}
