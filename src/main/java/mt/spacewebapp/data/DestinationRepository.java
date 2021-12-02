package mt.spacewebapp.data;


import mt.spacewebapp.models.Destination;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DestinationRepository extends JpaRepository<Destination, Integer> {
    Destination findByNameIgnoreCase(String name);
}
