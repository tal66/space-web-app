package mt.spacewebapp.data;

import mt.spacewebapp.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<Review, Long> {
}
