package mt.spacewebapp.dto;

import lombok.Data;
import mt.spacewebapp.models.Customer;
import java.time.LocalDate;

@Data
public class ReviewDto {
    private Integer id;
    private String customerFirstName;
    private int stars;
    private String headline;
    private String text;
    private LocalDate dateCreated;
}
