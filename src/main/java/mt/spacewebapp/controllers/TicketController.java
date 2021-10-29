package mt.spacewebapp.controllers;


import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.controllers.shared.DtoUtil;
import mt.spacewebapp.dto.DestinationDto;
import mt.spacewebapp.dto.TicketDto;
import mt.spacewebapp.dto.TripDto;
import mt.spacewebapp.models.Customer;
import mt.spacewebapp.models.Destination;
import mt.spacewebapp.models.Ticket;
import mt.spacewebapp.models.Trip;
import mt.spacewebapp.models.enums.TicketStatus;
import mt.spacewebapp.models.forms.FormValidation;
import mt.spacewebapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Controller
public class TicketController {
    @Autowired
    private DtoUtil dtoUtil;
    private IDestinationService destinationService;
    private ITripService tripService;
    private ITicketService ticketService;
    private ICustomerService customerService;

    public TicketController(IDestinationService destinationService, ITripService tripService, ITicketService ticketService, ICustomerService customerService) {
        this.destinationService = destinationService;
        this.tripService = tripService;
        this.ticketService = ticketService;
        this.customerService = customerService;
    }


    @PostMapping("booking")
    @PreAuthorize("isAuthenticated()")
    public String book(@Valid @ModelAttribute TicketDto ticketDto, BindingResult errors, Model model, Authentication authentication) {
        if (errors.hasErrors()) {
            return "error";
        }
        Ticket ticket = toTicket(ticketDto);
        ticket.setCustomer(customerService.findByUserName(authentication.getName()));
        Ticket savedTicket = ticketService.save(ticket);
        log.info("saved: " + savedTicket);

        model.addAttribute("ticket", dtoUtil.map(ticket, TicketDto.class));
        return "booking";
    }

    private Ticket toTicket(TicketDto ticketDto){
        Ticket ticket = ticketService.create();
        Trip selectedTrip = tripService.findById(ticketDto.getTripId()).orElse(null);
        ticket.setTrip(selectedTrip);
        ticket.setTicketClass(ticketDto.getTicketClass());
        return ticket;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("my_tickets")
    public String myTickets(Model model, Authentication authentication){
        List<Ticket> tickets = customerService.getUserTickets(authentication.getName());
        model.addAttribute("tickets", dtoUtil.mapList(tickets, TicketDto.class));
        return "my_tickets";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("my_tickets/cancel")
    public String cancelTicket(Model model, @RequestParam String id, Authentication authentication){
        FormValidation formValidation = validateCancelTicketRequest(id, authentication);
        String message;

        if (formValidation.isValid()){
            ticketService.setTicketStatusById(id, TicketStatus.CANCELED_BY_CLIENT);
            message = "canceled order: " + id;
        } else {
            message = formValidation.getErrorMessage();
        }

        model.addAttribute("message", message);
        return myTickets(model, authentication);
    }

    private FormValidation validateCancelTicketRequest(String id, Authentication authentication){
        String message = "";

        try {
            UUID.fromString(id);
        } catch (IllegalArgumentException e){
            message = "ERROR: invalid UUID " + id;
            return new FormValidation(message, false);
        }

        Optional<Ticket> ticketOptional = ticketService.findById(id);
        if (ticketOptional.isEmpty()){
            message = "ERROR: not found " + id;
        } else {
            Customer ticketCustomer = ticketOptional.get().getCustomer();
            String authUsername = authentication.getName();
            if (!ticketCustomer.getUserName().equals(authUsername)){
                message = "ERROR: not authorized to edit " + id;
            }
        }

        return new FormValidation(message, message.isEmpty());
    }

    @GetMapping("destination/{destinationName}")
    public String destinationInfoAndBookingForm(Model model, @PathVariable(value="destinationName") String destinationName, Authentication authentication){
        Destination destination = destinationService.findByName(destinationName);
        model.addAttribute("destination", dtoUtil.map(destination, DestinationDto.class));
        model.addAttribute("isLoggedIn", authentication != null);
        model.addAttribute("ticket", dtoUtil.map(ticketService.create(), TicketDto.class));
        model.addAttribute("classOptions", ticketService.ticketClassOptions());

        List<Trip> trips = tripService.getAvailableTripsToDestination(destination);
        model.addAttribute("trips", dtoUtil.mapList(trips, TripDto.class));
        return "destination";
    }


}
