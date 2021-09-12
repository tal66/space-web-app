package mt.spacewebapp.data;


import mt.spacewebapp.models.Destination;
import mt.spacewebapp.models.enums.DestinationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DestinationRepository extends JpaRepository<Destination, Integer> {
    Destination findByName(String name);
}
