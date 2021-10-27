package mt.spacewebapp.services;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.data.NewsArticleRepository;
import mt.spacewebapp.models.NewsArticle;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NewsService {
    private NewsArticleRepository newsArticleRepository;

    public NewsService(NewsArticleRepository newsArticleRepository) {
        this.newsArticleRepository = newsArticleRepository;
    }

    public NewsArticle save(NewsArticle article){
        log.info("saving posted news article");
        return newsArticleRepository.save(article);
    }

    public NewsArticle create(){
        return new NewsArticle();
    }

    @Cacheable("news_articles")
    public List<NewsArticle> findAll(){return newsArticleRepository.findAll();}
}
