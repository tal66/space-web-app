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
    public List<TicketDto> get(@PathVariable String username, Authentication authentication){
        log.info("api call: get tickets for user " + username);
        if (!username.equals(authentication.getName())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "mismatch bet. authenticated and requested username");
        }
        List<Ticket> tickets = customerService.getUserTickets(authentication.getName());
        return dtoUtil.mapList(tickets, TicketDto.class);
    }

}
