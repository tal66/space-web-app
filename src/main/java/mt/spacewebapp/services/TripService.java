package mt.spacewebapp.services;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.data.TripRepository;
import mt.spacewebapp.models.Destination;
import mt.spacewebapp.models.Trip;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TripService implements ITripService {

    private TripRepository tripRepository;
    private ITicketService ticketService;

    public TripService(TripRepository tripRepository, ITicketService ticketService) {
        this.tripRepository = tripRepository;
        this.ticketService = ticketService;
    }

    @Override //  n+1
    public List<Trip> getAvailableTripsToDestination(Destination destination){
        List<Trip> tripList = findByDestination(destination);
        List<Trip> availableTrips = tripList.stream()
                .filter(this::hasAvailableTickets)
                .collect(Collectors.toList());
        return availableTrips;
    }

    @Override
    public boolean hasAvailableTickets (Trip trip){
        return getNumberOfTicketsAvailable(trip) > 0;
    }

    @Override
    public int getNumberOfTicketsAvailable(Trip trip){
        int capacity = trip.getPlannedNumberOfPassengers();
        int sold = ticketService.countByTicketStatusValidAndTripId(trip.getId());
        return capacity - sold;
    }

    @Override
    public List<Trip> findByDestination(Destination destination){
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
