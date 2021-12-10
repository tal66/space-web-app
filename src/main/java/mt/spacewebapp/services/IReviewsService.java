package mt.spacewebapp.services;

import mt.spacewebapp.models.Review;

import java.util.List;

public interface IReviewsService {
    Review create();
    Review save(Review review);
    List<Review> findAll();
}
