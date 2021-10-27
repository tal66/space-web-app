package mt.spacewebapp.controllers;


import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.controllers.shared.DtoUtil;
import mt.spacewebapp.dto.DestinationDto;
import mt.spacewebapp.dto.TicketDto;
import mt.spacewebapp.dto.TripDto;
import mt.spacewebapp.models.Destination;
import mt.spacewebapp.models.Ticket;
import mt.spacewebapp.models.Trip;
import mt.spacewebapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private DtoUtil dtoUtil;
    private IDestinationService destinationService;
    private ITripService tripService;
    private ITicketService ITicketService;
    private ICustomerService customerService;

    public TicketController(IDestinationService destinationService, ITripService tripService, ITicketService ticketService, ICustomerService customerService) {
        this.destinationService = destinationService;
        this.tripService = tripService;
        this.ITicketService = ticketService;
        this.customerService = customerService;
    }


    @PostMapping("booking")
    @PreAuthorize("isAuthenticated()")
    public String bookingSubmit(@Valid @ModelAttribute TicketDto ticketDto, BindingResult errors, Model model, Authentication authentication) {
        if (errors.hasErrors()) {
            return "error";
        }
        Ticket ticket = toTicket(ticketDto);
        ticket.setCustomer(customerService.findByUserName(authentication.getName()));
        Ticket savedTicket = ITicketService.save(ticket);
        log.info("saved: " + savedTicket);

        model.addAttribute("ticket", dtoUtil.map(ticket, TicketDto.class));
        return "booking";
    }

    private Ticket toTicket(TicketDto ticketDto){
        Ticket ticket = ITicketService.create();
        Trip selectedTrip = tripService.findById(ticketDto.getTripId()).get();
        ticket.setTrip(selectedTrip);
        ticket.setTicketClass(ticketDto.getTicketClass());
        return ticket;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("my_tickets")
    public String myTickets(Model model, Authentication authentication){
        List<Ticket> tickets = customerService.getUserTickets(authentication.getName());
        log.info(tickets.toString());
        model.addAttribute("tickets", dtoUtil.mapList(tickets, TicketDto.class));
        return "my_tickets";
    }


    @GetMapping("destination/{destinationName}")
    public String destinationInfoAndBookingForm(Model model, @PathVariable(value="destinationName") String destinationName, Authentication authentication){
        Destination destination = destinationService.findByName(destinationName);
        model.addAttribute("destination", dtoUtil.map(destination, DestinationDto.class));
        model.addAttribute("isLoggedIn", authentication != null);
        model.addAttribute("ticket", dtoUtil.map(ITicketService.create(), TicketDto.class));
        model.addAttribute("classOptions", ITicketService.ticketClassOptions());

        List<Trip> trips = tripService.availableTripsToDestination(destination);
        model.addAttribute("trips", dtoUtil.mapList(trips, TripDto.class));
        return "destination";
    }


}
