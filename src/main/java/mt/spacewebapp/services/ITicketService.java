package mt.spacewebapp.services;

import mt.spacewebapp.models.Ticket;
import mt.spacewebapp.models.enums.TicketClass;

import java.util.List;
import java.util.Optional;

public interface ITicketService {
    Ticket save(Ticket ticket);

    Ticket create();

    TicketClass[] ticketClassOptions();

    List<Ticket> findAll();

    Optional<Ticket> findById(String id);
}
