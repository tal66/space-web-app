package mt.spacewebapp.services.impl;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.data.TicketRepository;
import mt.spacewebapp.models.Customer;
import mt.spacewebapp.models.Ticket;
import mt.spacewebapp.models.enums.TicketStatus;
import mt.spacewebapp.services.ITicketService;
import mt.spacewebapp.services.ITripService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class TicketService implements ITicketService {
    private TicketRepository ticketRepository;
    public ITripService tripService;

    public TicketService(TicketRepository ticketRepository, ITripService tripService) {
        this.ticketRepository = ticketRepository;
        this.tripService = tripService;
    }

    @Override
    public Ticket save(Ticket ticket){
        return tripService.saveTicketRequest(ticket);
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
        tripService.saveTicketRequest(ticket);
        return true;
    }

    @Override
    public List<Ticket> findAllOrderByTripId(){
        return ticketRepository.findAllByOrderByTripId();
    }

    @Override
    public List<Ticket> findByCustomer(Customer customer){
        return ticketRepository.findByCustomer(customer);
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
