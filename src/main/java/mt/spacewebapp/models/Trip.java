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
    private static final int DEFAULT_PLANNED_NUMBER_OF_PASSENGERS = 50;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    private int nTicketsSold;
    private int nTicketsMax;

    public Trip() {
        this.nTicketsMax = DEFAULT_PLANNED_NUMBER_OF_PASSENGERS;
    }

    public Trip(Destination from, Destination to, LocalDate date) {
        this();
        this.from = from;
        this.to = to;
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("Trip[%d, %s, %s, %s]", this.id, this.from.getName(), this.to.getName(), this.date);
    }

    public Integer getId() {
        return id;
    }

    public Destination getFrom() {
        return from;
    }

    public Destination getTo() {
        return to;
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

    public int getnTicketsMax() {
        return nTicketsMax;
    }

    public void setnTicketsMax(short nTicketsMax) {
        this.nTicketsMax = nTicketsMax;
    }

    public int getnTicketsSold() {
        return nTicketsSold;
    }

    public void setnTicketsSold(int nTicketsSold) {
        if (nTicketsSold < 0) {
            nTicketsSold = 0;
        } else if (nTicketsSold > nTicketsMax){
            return;
        }
        this.nTicketsSold = nTicketsSold;
    }

    public void incrementTicketsSoldBy(int n){
        setnTicketsSold(getnTicketsSold() + n);
    }
}
