package mt.spacewebapp.controllers;


import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.models.Destination;
import mt.spacewebapp.models.Ticket;
import mt.spacewebapp.models.Trip;
import mt.spacewebapp.services.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class TicketController {

    private final IDestinationService destinationService;
    private final ITripService tripService;
    private final ITicketService ITicketService;
    private final ICustomerService customerService;

    public TicketController(IDestinationService destinationService, ITripService tripService, ITicketService ticketService, ICustomerService customerService) {
        this.destinationService = destinationService;
        this.tripService = tripService;
        this.ITicketService = ticketService;
        this.customerService = customerService;
    }

    @PostMapping("booking")
    @PreAuthorize("isAuthenticated()")
    public String bookingSubmit(@Valid @ModelAttribute Ticket ticket, BindingResult errors, Model model, Authentication authentication) {
        if (errors.hasErrors()) {
            return "error";
        }
        ticket.setCustomer(customerService.findByUserName(authentication.getName()));
        Ticket savedTicket = ITicketService.save(ticket);
        log.info("saved: " + savedTicket);
        model.addAttribute("ticket", ticket);
        return "booking";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("my_tickets")
    public String myTickets(Model model, Authentication authentication){
        List<Ticket> tickets = customerService.getUserTickets(authentication.getName());
        log.info(tickets.toString());
        model.addAttribute("tickets", tickets);
        return "my_tickets";
    }


    @GetMapping("destination/{destinationName}")
    public String destinationInfoAndBookingForm(Model model, @PathVariable(value="destinationName") String destinationName, Authentication authentication){
        Destination destination = destinationService.findByName(destinationName);
        model.addAttribute("destination", destination);
        model.addAttribute("isLoggedIn", authentication != null);

        model.addAttribute("ticket", ITicketService.create());
        model.addAttribute("classOptions", ITicketService.ticketClassOptions());

        List<Trip> tripList = tripService.availableTripsToDestination(destination);
        model.addAttribute("trips", tripList);
        return "destination";
    }


}
