package mt.spacewebapp.services;

import mt.spacewebapp.models.Destination;
import mt.spacewebapp.models.Ticket;
import mt.spacewebapp.models.Trip;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ITripService {
    List<Trip> findAll();
    Optional<Trip> findById(Integer id);
    List<Trip> findByDateBetweenOrderByDate(LocalDate date1, LocalDate date2);
    List<Trip> findByDestinationIfAvailable(Destination destination);
    int getNumTicketsAvailable(Trip trip);
    Ticket saveTicketRequest(Ticket ticket);
}
