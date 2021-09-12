package mt.spacewebapp.data;


import mt.spacewebapp.models.Customer;
import mt.spacewebapp.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    public List<Ticket> findByCustomer(Customer customer);
}
