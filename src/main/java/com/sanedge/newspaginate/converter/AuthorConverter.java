package com.sanedge.newspaginate.converter;

import org.springframework.stereotype.Component;

import com.sanedge.newspaginate.domain.Article;
import com.sanedge.newspaginate.domain.Author;
import com.sanedge.newspaginate.dto.article.ArticleBaseDto;
import com.sanedge.newspaginate.dto.author.AuthorBaseDto;
import com.sanedge.newspaginate.dto.author.AuthorDto;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorConverter {
    public AuthorDto toDto(Author author) {
        return new AuthorDto(author.getName(), author.getLastname(), author.getCreateAt(), author.getFullName(),
                toListArticleDto(author.getArticles()));

    }

    public List<ArticleBaseDto> toListArticleDto(List<Article> articles) {

        List<ArticleBaseDto> articleDTOS = articles.stream().map(article -> toArticleBaseDTO(article))
                .collect(Collectors.toList());
        return articleDTOS;
    }

    private ArticleBaseDto toArticleBaseDTO(Article article) {
        return new ArticleBaseDto(article.getId(), article.getTitle(), article.getDescription(), article.getUrl(),
                article.getUrlToImage(), article.getContent(), article.getPublishedAt(), article.getPublished());
    }

    public AuthorBaseDto toDtoAuthorBase(Author author) {
        return new AuthorBaseDto(author.getName(), author.getLastname(), author.getCreateAt());
    }
}
