package mt.spacewebapp.services;

import mt.spacewebapp.models.Ticket;
import mt.spacewebapp.models.enums.TicketClass;
import mt.spacewebapp.models.enums.TicketStatus;

import java.util.List;
import java.util.Optional;

public interface ITicketService {
    List<Ticket> findAll();
    Optional<Ticket> findById(String id);

    Ticket save(Ticket ticket);
    boolean setTicketStatusById(String id, TicketStatus status);
    Ticket create();

    TicketClass[] ticketClassOptions();
}
