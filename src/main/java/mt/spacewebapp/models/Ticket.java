package mt.spacewebapp.models;

import mt.spacewebapp.models.converters.InstantConverter;
import mt.spacewebapp.models.enums.TicketClass;
import mt.spacewebapp.models.converters.TicketClassConverter;
import mt.spacewebapp.models.enums.TicketStatus;
import mt.spacewebapp.models.converters.TicketStatusConverter;

import javax.persistence.*;
import java.time.Instant;
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

    @Convert(converter = TicketStatusConverter.class)
    private TicketStatus status;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Convert(converter = InstantConverter.class)
    private final Instant bookingDateTime = Instant.now();


    public Ticket() {
    }

    public Ticket(TicketClass ticketClass, Trip trip) {
        this.ticketClass = ticketClass;
        this.trip = trip;
    }


    @Override
    public String toString() {
        return String.format("Ticket[%s, %s, trip: %d, to: %s, user: %s]",
                id, ticketClass, trip.getId(), trip.getTo().getName(), customer.getFirstName());
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

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public Instant getBookingDateTime() {
        return bookingDateTime;
    }
}
