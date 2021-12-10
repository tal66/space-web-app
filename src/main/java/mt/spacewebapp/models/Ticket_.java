package mt.spacewebapp.models;

import mt.spacewebapp.models.enums.TicketClass;
import mt.spacewebapp.models.enums.TicketStatus;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.UUID;

@StaticMetamodel(Ticket.class) // just trying this out
public class Ticket_ {
    public static volatile SingularAttribute<Ticket, UUID> id;
    public static volatile SingularAttribute<Ticket, TicketClass> ticketClass;
    public static volatile SingularAttribute<Ticket, TicketStatus> status;
    public static volatile SingularAttribute<Ticket, Trip> trip;
    public static volatile SingularAttribute<Ticket, Customer> customer;
}
