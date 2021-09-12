package mt.spacewebapp.data;

import mt.spacewebapp.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public Customer findByUserName(String userName);
    public Optional<Customer> findById(Long id);
}
