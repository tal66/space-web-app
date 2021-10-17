package mt.spacewebapp.services;

import mt.spacewebapp.models.Destination;
import mt.spacewebapp.models.Trip;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ITripService {
    List<Trip> availableTripsToDestination(Destination destination);

    List<Trip> findByDestination(Destination destination);
    Optional<Trip> findById(Integer id);
    List<Trip> findAll();

    List<Trip> findByDateAfterAndDateBeforeOrderByDate(LocalDate date1, LocalDate date2);
}
