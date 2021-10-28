package mt.spacewebapp.services;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.data.TicketRepository;
import mt.spacewebapp.models.Ticket;
import mt.spacewebapp.models.enums.TicketClass;
import mt.spacewebapp.models.enums.TicketStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class TicketService implements ITicketService {
    private TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket save(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket create(){
        Ticket ticket = new Ticket();
        ticket.setStatus(TicketStatus.VALID);
        return ticket;
    }

    @Override
    public boolean setTicketStatusById(String id, TicketStatus status){
        Optional<Ticket> ticketOptional = findById(id);
        if (ticketOptional.isEmpty()){
            return false;
        }
        Ticket ticket = ticketOptional.get();
        ticket.setStatus(status);
        save(ticket);
        return true;
    }

    private boolean deleteTicketById(String id){
        try {
            ticketRepository.deleteById(UUID.fromString(id));
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public TicketClass[] ticketClassOptions(){
        return TicketClass.values();
    }

    @Override
    public List<Ticket> findAll(){
        return ticketRepository.findAll();
    }

    @Override
    public Optional<Ticket> findById(String id) {
        UUID uuid = UUID.fromString(id);
        return ticketRepository.findById(uuid);
    }

}
