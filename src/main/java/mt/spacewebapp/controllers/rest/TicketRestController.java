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
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
@Slf4j
public class TicketRestController {
    @Autowired
    private DtoUtil dtoUtil;
    private ITicketService ticketService;

    public TicketRestController(ITicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public SimpleResponseWrapper<List<TicketDto>> getAllTickets(){
        log.info("api call: get all tickets");
        List<Ticket> tickets = ticketService.findAllOrderByTripId();
        List<TicketDto> ticketDtos = dtoUtil.mapList(tickets, TicketDto.class);

        return new SimpleResponseWrapper(ticketDtos,
                tickets.size() + " tickets");
    }

    @GetMapping("/{id}")
    public SimpleResponseWrapper<TicketDto> getById(@PathVariable String id){
        log.info(String.format("api call: get ticket __ %s __", id));

        Optional<Ticket> ticket = ticketService.findById(id);
        if (ticket.isEmpty()){
            return new SimpleResponseWrapper("ticket not found");
        }

        TicketDto ticketDto = dtoUtil.map(ticket.get(), TicketDto.class);
        return new SimpleResponseWrapper(ticketDto);
    }

}
