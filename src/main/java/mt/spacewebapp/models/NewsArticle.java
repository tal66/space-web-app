package mt.spacewebapp.models;


import mt.spacewebapp.models.Image;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "news_articles")
public class NewsArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String headline;
    private String text;


//    @ElementCollection
//    @CollectionTable(name = "article_images",
//            joinColumns = {@JoinColumn(name = "article_id", referencedColumnName = "id")})
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "news_article_images",
            joinColumns = @JoinColumn(name = "news_article_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private List<Image> imageList;

    private final LocalDate dateCreated = LocalDate.now();

    public NewsArticle() {
    }

    public NewsArticle(String headline, List<Image> imageList, String text) {
        this.headline = headline;
        this.imageList = imageList;
        this.text = text;
    }

    public void addImage(String address, String text){
        imageList.add(new Image(address, text));
    }

    public boolean removeImage(int index){
        if (index >=0 && index < imageList.size()){
            imageList.remove(index);
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "NewsArticle{" +
                "id=" + id +
                ", headline='" + headline + '\'' +
                ", text='" + text + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public List<Image> getImageList() {
        return imageList;
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
