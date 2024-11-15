package com.sanedge.newspaginate.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sanedge.newspaginate.converter.ArticleConverter;
import com.sanedge.newspaginate.domain.Article;
import com.sanedge.newspaginate.dto.article.ArticleBaseDto;
import com.sanedge.newspaginate.repository.ArticleRepository;
import com.sanedge.newspaginate.utils.PageArticle;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;

    private ArticleConverter articleConverter;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, ArticleConverter articleConverter) {
        this.articleRepository = articleRepository;
        this.articleConverter = articleConverter;
    }

    public ArticleBaseDto createArticle(Article article) {
        Article articleNew = articleRepository.save(article);

        return articleConverter.toDtoArticleBase(articleNew);
    }

    public PageArticle getAllArticle(int page, Boolean published, String query) {
        Pageable pageable = PageRequest.of(page - 1, 3);
        PageArticle pageCustumerArticle = new PageArticle();
        if (query != null) {
            Page<Article> authorsPaged = articleRepository.searchArticle(query, pageable);
            pageCustumerArticle.setTotalResult(authorsPaged.getTotalElements());
            pageCustumerArticle.setPage(authorsPaged.getTotalPages());
            pageCustumerArticle.setSize(authorsPaged.getSize());
            pageCustumerArticle.setStatus(HttpStatus.OK);
            pageCustumerArticle.setContent(authorsPaged.getContent().stream()
                    .map(article -> articleConverter.toDto(article)).collect(Collectors.toList()));
        } else {

            Page<Article> authorsPaged = articleRepository.findAllByPublished(published, pageable);

            pageCustumerArticle.setTotalResult(authorsPaged.getTotalElements());
            pageCustumerArticle.setPage(authorsPaged.getTotalPages());
            pageCustumerArticle.setSize(authorsPaged.getSize());
            pageCustumerArticle.setStatus(HttpStatus.OK);
            pageCustumerArticle.setContent(authorsPaged.getContent().stream()
                    .map(article -> articleConverter.toDto(article)).collect(Collectors.toList()));
        }
        return pageCustumerArticle;
    }

    public String deleteArticle(Integer id) {
        articleRepository.deleteById(id);
        String confirmationMessage = "deleted author with id:" + id;
        return confirmationMessage;
    }

    public ArticleBaseDto updateArticle(Integer id, Article article) {
        Article articleSelect = articleRepository.findById(id).orElse(null);
        articleSelect.setId(id);
        articleSelect.setTitle(article.getTitle());
        articleSelect.setDescription(article.getDescription());
        Article articleUpdated = articleRepository.save(articleSelect);
        return articleConverter.toDto(articleUpdated);
    }
}
