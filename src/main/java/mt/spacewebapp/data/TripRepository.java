package mt.spacewebapp.data;

import mt.spacewebapp.models.Destination;
import mt.spacewebapp.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Integer> {
    List<Trip> findByTo(Destination destination);
    List<Trip> findByDateBetweenOrderByDate(LocalDate start, LocalDate end);
}
