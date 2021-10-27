package mt.spacewebapp.controllers.rest;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.controllers.shared.DtoUtil;
import mt.spacewebapp.dto.TicketDto;
import mt.spacewebapp.models.Ticket;
import mt.spacewebapp.services.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@Slf4j
public class TicketRestController {
    @Autowired
    private DtoUtil dtoUtil;
    private ITicketService ITicketService;

    public TicketRestController(ITicketService ticketService) {
        this.ITicketService = ticketService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<TicketDto> getAllTickets(){
        log.info("api call: get all tickets");
        List<Ticket> tickets = ITicketService.findAll();
        return dtoUtil.mapList(tickets, TicketDto.class);
    }

    @GetMapping("/{id}")
    public TicketDto getById(@PathVariable String id){
        log.info("api call: get ticket " + id);
        Ticket ticket = ITicketService.findById(id).get();
        return dtoUtil.map(ticket, TicketDto.class);
    }

}
