package mt.spacewebapp.services;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.data.ReviewsRepository;
import mt.spacewebapp.models.Review;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReviewsService {
    private ReviewsRepository reviewsRepository;
    private int DEFAULT_STARS = 5;

    public ReviewsService(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }

    public Review create(){
        Review newReview = new Review();
        newReview.setStars(DEFAULT_STARS);
        return newReview;
    }

    @CacheEvict(value = "reviews", allEntries = true)
    public Review save(Review review){
        log.info("saving review");
        return reviewsRepository.save(review);
    }

    @Cacheable(value = "reviews")
    public List<Review> findAll(){
        List<Review> reviews = reviewsRepository.findAll();
        return reviews;
    }
}
