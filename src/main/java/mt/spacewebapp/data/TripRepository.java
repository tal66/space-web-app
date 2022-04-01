package mt.spacewebapp.data;

import mt.spacewebapp.models.Destination;
import mt.spacewebapp.models.Trip;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Integer> {
    @EntityGraph(attributePaths = {"from"})
    List<Trip> findByTo(Destination destination);
    @EntityGraph(attributePaths = {"to","from"})
    List<Trip> findByToId(Integer destinationId);
    @EntityGraph(attributePaths = {"to", "from.name"})
    List<Trip> findByDateBetweenOrderByDate(LocalDate start, LocalDate end);
}
