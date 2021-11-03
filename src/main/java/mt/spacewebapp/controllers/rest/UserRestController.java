package mt.spacewebapp.controllers.rest;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.controllers.shared.DtoUtil;
import mt.spacewebapp.dto.TicketDto;
import mt.spacewebapp.models.Ticket;
import mt.spacewebapp.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserRestController {
    private ICustomerService customerService;
    @Autowired
    private DtoUtil dtoUtil;

    public UserRestController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{username}/tickets")
    public SimpleResponseWrapper<List<TicketDto>> get(@PathVariable String username, Authentication authentication){
        log.info(String.format("api call: get tickets for user __ %s __", username));
        if (!username.equals(authentication.getName())){
            String message = "authenticated username and requested username do not match";
            log.error(message);
            return new SimpleResponseWrapper (message);
        }
        List<Ticket> tickets = customerService.getUserTickets(authentication.getName());
        List<TicketDto> ticketDtos = dtoUtil.mapList(tickets, TicketDto.class);
        return new SimpleResponseWrapper(ticketDtos,
                tickets.size() + " tickets");
    }

}
