package mt.spacewebapp.services;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.data.TicketRepository;
import mt.spacewebapp.models.Ticket;
import mt.spacewebapp.models.enums.TicketClass;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class TicketService {
    private TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket save(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    public Ticket create(){
        return new Ticket();
    }

    public TicketClass[] ticketClassOptions(){
        return TicketClass.values();
    }

    public List<Ticket> findAll(){
        return ticketRepository.findAll();
    }

    public Optional<Ticket> findById(String id) {
        UUID uuid = UUID.fromString(id);
        return ticketRepository.findById(uuid);
    }

}
