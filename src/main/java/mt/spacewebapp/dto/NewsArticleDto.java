package mt.spacewebapp.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class NewsArticleDto {
    private Integer id;
    private String headline;
    private String text;
    private LocalDate dateCreated;
    private Set<ImageDto> images;
}
