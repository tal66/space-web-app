package mt.spacewebapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Trips")
@Slf4j
public class Trip {
    private static final int DEFAULT_NUMBER_OF_PASSENGERS = 50;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tripId;

    @ManyToOne
    @JoinColumn (name = "from_dest")
    private Destination from;

    @ManyToOne
    @JoinColumn (name = "to_dest")
    private Destination to;

    @Column(name = "trip_date")
    private LocalDate date;

    @JsonIgnore
    @OneToMany(mappedBy = "trip", fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    private int numberOfPassengers;

    public Trip() {
        this.numberOfPassengers = DEFAULT_NUMBER_OF_PASSENGERS;
    }

    public Trip(Destination from, Destination to, LocalDate date) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.numberOfPassengers = DEFAULT_NUMBER_OF_PASSENGERS;
    }

    public boolean isAvailable(){
        int available = numberOfPassengers - tickets.size();
        log.debug(String.format("trip %d: %d available", tripId, available));
        return available > 0;
    }


    @Override
    public String toString() {
        return String.format("Trip[%d, %s, %s, %s]", this.tripId, this.from.getName(), this.to.getName(), this.date);
    }

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public Destination getFrom() {
        return from;
    }

    public void setFrom(Destination from) {
        this.from = from;
    }

    public Destination getTo() {
        return to;
    }

    public void setTo(Destination to) {
        this.to = to;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }
}
