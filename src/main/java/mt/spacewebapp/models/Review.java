package mt.spacewebapp.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Max(value = 5)
    @Min(value = 1)
    private int stars;
    @Size(max = 20)
    private String headline;
    @Size(max = 400)
    private String text;
    private final LocalDate dateCreated = LocalDate.now();

    public Review() {
    }

    public Review(Customer customer, @Max(value = 5, message = "maximum 5") @Min(value = 1, message = "minimum 1") int stars,
                  @Size(max = 20, message = "maximum 20") String headline, @Size(max = 400) String text) {
        this.customer = customer;
        this.stars = stars;
        this.headline = headline;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }
}
