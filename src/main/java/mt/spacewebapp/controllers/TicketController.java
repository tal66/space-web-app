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
import mt.spacewebapp.models.enums.TicketClass;
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
            log.warn("booking binding error");
            return "error";
        }

        Ticket ticket = toTicket(ticketDto);
        ticket.setCustomer(customerService.findByUserName(authentication.getName()));
        Ticket savedTicket = ticketService.save(ticket);
        log.info("saved: " + savedTicket);

        model.addAttribute("ticket", dtoUtil.map(savedTicket, TicketDto.class));
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
        List<TicketDto> ticketDtos = dtoUtil.mapList(tickets, TicketDto.class);
        model.addAttribute("tickets", ticketDtos);
        return "my_tickets";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("my_tickets/cancel")
    public String cancelTicket(Model model, @RequestParam String id, Authentication authentication){
        FormValidation formValidation = validateCancelTicketRequest(id, authentication);
        String message;

        if (formValidation.isValid()){
            ticketService.cancelTicket(id);
            message = "canceled order: " + id;
        } else {
            message = formValidation.getErrorMessage();
        }

        model.addAttribute("message", message);
        return myTickets(model, authentication);
    }

    private FormValidation validateCancelTicketRequest(String id, Authentication authentication){
        String message = "";
        String errorMessage = "ERROR: ticket not found " + id;

        try {
            UUID.fromString(id);
        } catch (IllegalArgumentException e){
            log.error(String.format("invalid ticket UUID __ %s __", id));
            return new FormValidation(errorMessage, false);
        }

        Optional<Ticket> ticketOptional = ticketService.findById(id);
        if (ticketOptional.isEmpty()){
            message = errorMessage;
            log.error(String.format("ticket not found __ %s __", id));
        } else {
            Customer ticketCustomer = ticketOptional.get().getCustomer();
            String authUsername = authentication.getName();
            if (!ticketCustomer.getUserName().equals(authUsername)){
                message = errorMessage;
                log.error(String.format("not authorized to cancel ticket. username: %s , ticket: __ %s __", authUsername, id));
            }
        }

        return new FormValidation(message, message.isEmpty());
    }

    @GetMapping("destination/{destinationName}")
    public String destinationInfoAndBookingForm(Model model, @PathVariable(value="destinationName") String destinationName,
                                                Authentication authentication){
        Destination destination = destinationService.findByName(destinationName);
        if (destination == null){
            model.addAttribute("message", "destination not found.");
            return "error";
        }

        model.addAttribute("destination", dtoUtil.map(destination, DestinationDto.class));
        model.addAttribute("isLoggedIn", authentication != null);
        model.addAttribute("ticket", dtoUtil.map(ticketService.create(), TicketDto.class));
        model.addAttribute("classOptions", TicketClass.values());

        List<Trip> trips = tripService.findByDestinationIfAvailable(destination);
        model.addAttribute("trips", dtoUtil.mapList(trips, TripDto.class));
        return "destination";
    }


}
