package mt.spacewebapp.controllers.rest;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.models.Ticket;
import mt.spacewebapp.services.TicketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@Slf4j
public class TicketRestController {
    private final TicketService ticketService;


    public TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> getAllTrips(){
        log.info("api call: get all tickets");
        return ticketService.findAll();
    }

    @GetMapping("/{id}")
    public Ticket get(@PathVariable String id){
        log.info("api call: get ticket " + id);
        Ticket ticket = ticketService.findById(id).get();
        return ticket;
    }

}
