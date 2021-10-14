package mt.spacewebapp.controllers;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.models.NewsArticle;
import mt.spacewebapp.services.NewsService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class NewsController {
    private NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news")
    public String news(Model model){
        addAllArticlesToModel(model);
        return "news";
    }

    private void addAllArticlesToModel(Model model){
        List<NewsArticle> articles = newsService.findAll();
        model.addAttribute("articles", articles);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @CacheEvict(value = "news_articles", allEntries = true)
    @PostMapping(value="/news", params = "add-news")
    public String showFormAddNewsArticle(Model model){
        NewsArticle newArticle = new NewsArticle();
        model.addAttribute("newArticle", newArticle);
        model.addAttribute("showArticleForm", true);
        addAllArticlesToModel(model);
        return "news";
    }

    @PostMapping(value="/news", params = "submit-news")
    public String submitNewsArticle(Model model, @Valid @ModelAttribute NewsArticle article, BindingResult errors){
        newsService.save(article);
        addAllArticlesToModel(model);
        return "news";
    }

}
