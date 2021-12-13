package mt.spacewebapp.data;

import mt.spacewebapp.models.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Review, Long> {
    @EntityGraph(attributePaths = {"customer"})
    List<Review> findAll();
}
