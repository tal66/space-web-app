package mt.spacewebapp.services;

import mt.spacewebapp.data.ReviewsRepository;
import mt.spacewebapp.models.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsService {
    ReviewsRepository reviewsRepository;

    public ReviewsService(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }

    public List<Review> findAll(){
        return reviewsRepository.findAll();
    }

    public Review save(Review review){
        return reviewsRepository.save(review);
    }


}
