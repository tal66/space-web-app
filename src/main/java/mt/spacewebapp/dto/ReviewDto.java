package mt.spacewebapp.dto;

import lombok.Data;
import mt.spacewebapp.models.Customer;
import java.time.LocalDate;

@Data
public class ReviewDto {
    private Long id;
    private Customer customer;
    private int stars;
    private String headline;
    private String text;
    private LocalDate dateCreated;
}
