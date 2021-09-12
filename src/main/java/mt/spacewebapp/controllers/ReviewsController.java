package mt.spacewebapp.controllers;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.models.Review;
import mt.spacewebapp.services.CustomerService;
import mt.spacewebapp.services.ReviewsService;
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
    ReviewsService reviewsService;
    CustomerService customerService;

    public ReviewsController(ReviewsService reviewsService, CustomerService customerService) {
        this.reviewsService = reviewsService;
        this.customerService = customerService;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        List<Review> reviews = reviewsService.findAll();
        model.addAttribute("reviews", reviews);
    }

    @GetMapping("/reviews")
    public String reviews(Model model){
        return "reviews";
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value="/reviews", params = "add-review")
    public String showFormReview(Model model, Authentication authentication){
        Review newReview = new Review();
        newReview.setStars(5);
        newReview.setCustomer(customerService.findByUserName(authentication.getName()));
        model.addAttribute("newReview", newReview);
        model.addAttribute("showReviewForm", true);
        return "reviews";
    }

    @PostMapping(value="/reviews", params = "submit-review")
    public String submitReview(Model model, @Valid @ModelAttribute Review review, BindingResult errors, Authentication authentication){
        if (errors.hasErrors()){
            log.info(errors.toString());
            model.addAttribute("errors", errors.getFieldErrors());
            model.addAttribute("showReviewForm", true);
            model.addAttribute("newReview", review);
        } else {
            review.setCustomer(customerService.findByUserName(authentication.getName()));
            reviewsService.save(review);
        }
        addAttributes(model);
        return "reviews";
    }





}
