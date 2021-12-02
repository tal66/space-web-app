package mt.spacewebapp.services.impl;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.data.TicketRepository;
import mt.spacewebapp.data.Specs.TicketSpecs;
import mt.spacewebapp.models.Ticket;
import mt.spacewebapp.models.enums.TicketClass;
import mt.spacewebapp.models.enums.TicketStatus;
import mt.spacewebapp.services.ITicketService;
import org.springframework.data.jpa.domain.Specification;
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
    public boolean cancelTicket(String id){
        return setTicketStatusById(id, TicketStatus.CANCELED_BY_CLIENT);
    }

    private boolean setTicketStatusById(String id, TicketStatus status){
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
    public List<Ticket> findAllOrderByTripId(){
        return ticketRepository.findAllByOrderByTripId();
    }

    @Override
    public int countByTicketStatusValidAndTripId(Integer id){
        return (int)ticketRepository.count(Specification
                .where(TicketSpecs.whereTripId(id))
                .and(TicketSpecs.isValid()));
    }

    @Override
    public Optional<Ticket> findById(String id) {
        try {
            UUID uuid = UUID.fromString(id);
            return ticketRepository.findById(uuid);
        } catch (IllegalArgumentException e){
            return Optional.empty();
        }
    }

}
