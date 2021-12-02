package mt.spacewebapp.services.impl;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.data.NewsArticleRepository;
import mt.spacewebapp.models.NewsArticle;
import mt.spacewebapp.services.INewsService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NewsService implements INewsService {
    private NewsArticleRepository newsArticleRepository;

    public NewsService(NewsArticleRepository newsArticleRepository) {
        this.newsArticleRepository = newsArticleRepository;
    }


    @Override
    public NewsArticle create(){
        return new NewsArticle();
    }

    @Override
    @CacheEvict(value = "news_articles", allEntries = true)
    public NewsArticle save(NewsArticle article){
        log.info("saving posted article");
        return newsArticleRepository.save(article);
    }

    @Override
    @Cacheable("news_articles")
    public List<NewsArticle> findAll(){
        return newsArticleRepository.findAll();
    }

}
