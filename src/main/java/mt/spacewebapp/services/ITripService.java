package mt.spacewebapp.services;

import mt.spacewebapp.models.Destination;
import mt.spacewebapp.models.Trip;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ITripService {
    List<Trip> findAll();
    Optional<Trip> findById(Integer id);
    List<Trip> findByDestination(Destination destination);
    List<Trip> findByDateBetweenOrderByDate(LocalDate date1, LocalDate date2);

    List<Trip> getAvailableTripsToDestination(Destination destination);
    boolean hasAvailableTickets (Trip trip);
    int getNumberOfTicketsAvailable(Trip trip);
}
