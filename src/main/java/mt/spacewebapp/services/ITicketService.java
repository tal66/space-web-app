package mt.spacewebapp.services;

import mt.spacewebapp.models.Customer;
import mt.spacewebapp.models.Ticket;
import mt.spacewebapp.models.enums.TicketClass;
import mt.spacewebapp.models.enums.TicketStatus;

import java.util.List;
import java.util.Optional;

public interface ITicketService {
    List<Ticket> findAllOrderByTripId();
    Optional<Ticket> findById(String id);
    List<Ticket> findByCustomer(Customer customer);

    Ticket create();
    Ticket save(Ticket ticket);
    boolean cancelTicket(String id);
}
