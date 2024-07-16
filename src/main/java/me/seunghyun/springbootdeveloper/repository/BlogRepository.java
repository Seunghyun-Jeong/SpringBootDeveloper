package me.seunghyun.springbootdeveloper.repository;

import me.seunghyun.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
