package mt.spacewebapp.services;

import mt.spacewebapp.models.NewsArticle;

import java.util.List;

public interface INewsService {
    NewsArticle create();
    NewsArticle save(NewsArticle article);
    List<NewsArticle> findAll();
}
