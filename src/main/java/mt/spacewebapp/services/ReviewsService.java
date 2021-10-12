package mt.spacewebapp.services;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.data.ReviewsRepository;
import mt.spacewebapp.models.Review;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReviewsService {
    private ReviewsRepository reviewsRepository;
    private CacheService cacheService;

    public ReviewsService(ReviewsRepository reviewsRepository, CacheService cacheService) {
        this.reviewsRepository = reviewsRepository;
        this.cacheService = cacheService;
    }

    @Cacheable("reviews")
    public List<Review> findAll(){
        return reviewsRepository.findAll();
    }

    public Review save(Review review){
        cacheService.evictCacheValues("reviews");
        return reviewsRepository.save(review);
    }

}
