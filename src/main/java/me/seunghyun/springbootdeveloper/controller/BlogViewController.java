package me.seunghyun.springbootdeveloper.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.seunghyun.springbootdeveloper.dto.ArticleListViewResponse;
import me.seunghyun.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
