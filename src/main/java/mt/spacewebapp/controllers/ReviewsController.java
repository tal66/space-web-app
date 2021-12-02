package mt.spacewebapp.controllers;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.controllers.shared.DtoUtil;
import mt.spacewebapp.dto.ReviewDto;
import mt.spacewebapp.models.Review;
import mt.spacewebapp.services.ICustomerService;
import mt.spacewebapp.services.IReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class ReviewsController {
    @Autowired
    private DtoUtil dtoUtil;
    private IReviewsService reviewsService;
    private ICustomerService customerService;

    public ReviewsController(IReviewsService reviewsService, ICustomerService customerService) {
        this.reviewsService = reviewsService;
        this.customerService = customerService;
    }

    private void addReviewsAttributes(Model model) {
        List<Review> reviews = reviewsService.findAll();
        model.addAttribute("reviews", dtoUtil.mapList(reviews, ReviewDto.class));
    }

    @GetMapping("/reviews")
    public String reviews(Model model){
        addReviewsAttributes(model);
        return "reviews";
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value="/reviews", params = "add-review")
    public String showFormReview(Model model, Authentication authentication){
        Review newReview = reviewsService.create();
        newReview.setCustomer(customerService.findByUserName(authentication.getName()));
        model.addAttribute("newReview", dtoUtil.map(newReview, ReviewDto.class));
        model.addAttribute("showReviewForm", true);
        addReviewsAttributes(model);
        return "reviews";
    }

    @PostMapping(value="/reviews", params = "submit-review")
    public String submitReview(Model model, @Valid @ModelAttribute ReviewDto reviewDto, BindingResult errors, Authentication authentication){
        if (errors.hasErrors()){
            log.info(errors.toString());
            model.addAttribute("showReviewForm", true);
            model.addAttribute("newReview", reviewDto);
        } else {
            Review review = toReview(reviewDto);
            review.setCustomer(customerService.findByUserName(authentication.getName()));
            reviewsService.save(review);
        }
        addReviewsAttributes(model);
        return "reviews";
    }

    private Review toReview(ReviewDto reviewDto){
        Review review = reviewsService.create();
        review.setStars(reviewDto.getStars());
        review.setHeadline(reviewDto.getHeadline());
        review.setText(reviewDto.getText());
        return review;
    }


}
