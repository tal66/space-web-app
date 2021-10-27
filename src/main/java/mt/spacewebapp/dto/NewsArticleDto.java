package mt.spacewebapp.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class NewsArticleDto {
    private Integer id;
    private String headline;
    private String text;
    private LocalDate dateCreated;
    private List<ImageDto> imageList;
}
