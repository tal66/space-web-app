package mt.spacewebapp.services.impl;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.data.TicketRepository;
import mt.spacewebapp.data.TripRepository;
import mt.spacewebapp.models.Destination;
import mt.spacewebapp.models.Ticket;
import mt.spacewebapp.models.Trip;
import mt.spacewebapp.models.enums.TicketStatus;
import mt.spacewebapp.services.ITripService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class TripService implements ITripService {

    private TripRepository tripRepository;
    private TicketRepository ticketRepository;

    public TripService(TripRepository tripRepository, TicketRepository ticketRepository) {
        this.tripRepository = tripRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket saveTicketRequest(Ticket ticket){
        Trip trip = ticket.getTrip();
        int increment = 1;
        if (ticket.getStatus() != TicketStatus.VALID){
            increment = -1;
        }
        trip.incrementTicketsSoldBy(increment);
        Ticket savedTicket = ticketRepository.save(ticket);
        tripRepository.save(trip);
        return savedTicket;
    }

    @Override
    public List<Trip> findByDestinationIfAvailable(Destination destination){
        List<Trip> tripList = findByDestination(destination);
        List<Trip> availableTrips = tripList.stream()
                .filter(this::hasAvailableTickets)
                .collect(Collectors.toList());
        return availableTrips;
    }

    private boolean hasAvailableTickets (Trip trip){
        return this.getNumTicketsAvailable(trip) > 0;
    }

    @Override
    public int getNumTicketsAvailable(Trip trip){
        int capacity = trip.getnTicketsMax();
        int sold = trip.getnTicketsSold();
        return capacity - sold;
    }

    private List<Trip> findByDestination(Destination destination){
        List<Trip> tripList = tripRepository.findByTo(destination);
        log.info(String.format("All Trips to %s: %d", destination.getName(), tripList.size()));
        return tripList;
    }

    @Override
    public Optional<Trip> findById(Integer id){
        return tripRepository.findById(id);
    }

    @Override
    public List<Trip> findAll(){
        List<Trip> tripList = tripRepository.findAll();
        return tripList;
    }

    @Override
    public List<Trip> findByDateBetweenOrderByDate(LocalDate date1, LocalDate date2){
        return tripRepository.findByDateBetweenOrderByDate(date1, date2);
    }
}
