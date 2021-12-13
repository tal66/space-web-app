package mt.spacewebapp.data;

import mt.spacewebapp.models.Destination;
import mt.spacewebapp.models.Trip;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Integer> {
    @EntityGraph(attributePaths = {"from"})
    List<Trip> findByTo(Destination destination);
    @EntityGraph(attributePaths = {"to", "from.name"})
    List<Trip> findByDateBetweenOrderByDate(LocalDate start, LocalDate end);
}
