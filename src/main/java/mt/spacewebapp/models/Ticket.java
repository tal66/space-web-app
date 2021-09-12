package mt.spacewebapp.models;

import mt.spacewebapp.models.enums.DestinationTypeConverter;
import mt.spacewebapp.models.enums.TicketClass;
import mt.spacewebapp.models.enums.TicketClassConverter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Tickets")
public class Ticket {

    @Id
    @GeneratedValue
    private UUID id;

    @Convert(converter = TicketClassConverter.class)
    @Column(name = "class")
    private TicketClass ticketClass;

    @ManyToOne
    @JoinColumn(name = "tripId")
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public Ticket() {
    }

    public Ticket(TicketClass ticketClass, Trip trip) {
        this.ticketClass = ticketClass;
        this.trip = trip;
    }



    @Override
    public String toString() {
        return String.format("Ticket[%s, %s, trip: %d, to: %s, user: %s]", id, ticketClass, trip.getTripId(), trip.getTo().getName(), customer.getFirstName());
    }

    public UUID getId() {
        return id;
    }

    public TicketClass getTicketClass() {
        return ticketClass;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTicketClass(TicketClass ticketClass) {
        this.ticketClass = ticketClass;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
