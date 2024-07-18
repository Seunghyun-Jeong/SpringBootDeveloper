package me.seunghyun.springbootdeveloper.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.seunghyun.springbootdeveloper.domain.Article;
import me.seunghyun.springbootdeveloper.dto.ArticleListViewResponse;
import me.seunghyun.springbootdeveloper.dto.ArticleViewResponse;
import me.seunghyun.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class BlogViewController {
    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream().map(ArticleListViewResponse::new).toList();
        model.addAttribute("articles", articles);

        return "articlesList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    @GetMapping("/new-article")
    // id 키를 가진 쿼리 파라미터의 값을 id 변수에 매핑(id는 없을 수도 있음)
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) { // id가 없으면 생성
            model.addAttribute("article", new ArticleViewResponse());
        } else { // id가 없으면 수정
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        return "newArticle";
    }
}
