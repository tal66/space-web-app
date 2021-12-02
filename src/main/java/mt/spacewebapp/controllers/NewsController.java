package mt.spacewebapp.controllers;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.controllers.shared.DtoUtil;
import mt.spacewebapp.dto.NewsArticleDto;
import mt.spacewebapp.models.NewsArticle;
import mt.spacewebapp.services.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private DtoUtil dtoUtil;
    private INewsService newsService;

    public NewsController(INewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news")
    public String news(Model model){
        addAllArticlesToModel(model);
        return "news";
    }

    private void addAllArticlesToModel(Model model){
        List<NewsArticle> articles = newsService.findAll();
        model.addAttribute("articles", dtoUtil.mapList(articles, NewsArticleDto.class));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value="/news", params = "add-news")
    public String showFormAddNewsArticle(Model model){
        model.addAttribute("newArticle", new NewsArticleDto());
        model.addAttribute("showArticleForm", true);
        addAllArticlesToModel(model);
        return "news";
    }

    @PostMapping(value="/news", params = "submit-news")
    public String submitNewsArticle(Model model, @Valid @ModelAttribute NewsArticleDto articleDto, BindingResult errors){
        NewsArticle newsArticle = toNewsArticle(articleDto);
        newsService.save(newsArticle);
        addAllArticlesToModel(model);
        return "news";
    }

    private NewsArticle toNewsArticle (NewsArticleDto articleDto){
        NewsArticle newsArticle = newsService.create();
        newsArticle.setHeadline(articleDto.getHeadline());
        newsArticle.setText(articleDto.getText());
        return newsArticle;
    }

}
