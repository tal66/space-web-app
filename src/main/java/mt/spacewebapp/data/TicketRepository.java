package mt.spacewebapp.data;


import mt.spacewebapp.models.Customer;
import mt.spacewebapp.models.Ticket;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID>, JpaSpecificationExecutor<Ticket> {
    @EntityGraph(attributePaths = {"customer", "trip", "trip.to", "trip.from"})
    List<Ticket> findByCustomer(Customer customer);
    List<Ticket> findAllByOrderByTripId();
}
