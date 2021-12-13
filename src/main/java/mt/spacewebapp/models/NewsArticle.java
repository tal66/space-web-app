package mt.spacewebapp.models;


import lombok.ToString;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "news_articles")
public class NewsArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String headline;
    private String text;

    @ManyToMany
    @JoinTable(name = "news_article_images",
            joinColumns = @JoinColumn(name = "news_article_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> images;

    private final LocalDate dateCreated = LocalDate.now();

    public NewsArticle() {
    }

    public NewsArticle(String headline, Set<Image> images, String text) {
        this.headline = headline;
        this.images = images;
        this.text = text;
    }

    public void addImage(String address, String text){
        images.add(new Image(address, text));
    }

    public boolean removeImage(int index){
        if (index >=0 && index < images.size()){
            images.remove(index);
            return true;
        }
        return false;
    }

    public Set<Image> getImages() {
        return images;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
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
}
