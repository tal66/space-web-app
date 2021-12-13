package mt.spacewebapp.data;

import mt.spacewebapp.models.NewsArticle;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsArticleRepository extends JpaRepository<NewsArticle, Integer> {
    @EntityGraph(attributePaths = {"images"})
    List<NewsArticle> findAll();
}
