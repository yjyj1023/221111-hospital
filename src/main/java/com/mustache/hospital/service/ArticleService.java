package com.mustache.hospital.service;

import com.mustache.hospital.domain.Article;
import com.mustache.hospital.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getBoardList(){
        return articleRepository.findAll();
    }

    public Article save(Article article){
        return articleRepository.save(article);
    }

    public Optional<Article> findById(Long id){
        Optional<Article> optArticle = articleRepository.findById(id);
        return optArticle;
    }

    public void delete(Long id){
        articleRepository.deleteById(id);
    }
}
