package mt.spacewebapp.controllers.rest;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.models.Ticket;
import mt.spacewebapp.services.CustomerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserRestController {
    private CustomerService customerService;

    public UserRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{username}/tickets")

    public List<Ticket> get(@PathVariable String username, Authentication authentication){
        log.info("api call: get tickets for user " + username);
        List<Ticket> tickets = customerService.getUserTickets(authentication.getName());
        return tickets;
    }

}
