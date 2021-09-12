package mt.spacewebapp.data;

import mt.spacewebapp.models.NewsArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsArticleRepository extends JpaRepository<NewsArticle, Integer> {
}
