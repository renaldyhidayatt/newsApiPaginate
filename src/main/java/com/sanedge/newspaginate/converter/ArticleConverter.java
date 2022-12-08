package com.sanedge.newspaginate.converter;

import org.springframework.stereotype.Component;

import com.sanedge.newspaginate.domain.Article;
import com.sanedge.newspaginate.domain.Author;
import com.sanedge.newspaginate.domain.Source;
import com.sanedge.newspaginate.dto.article.ArticleBaseDto;
import com.sanedge.newspaginate.dto.author.AuthorBaseDto;
import com.sanedge.newspaginate.dto.article.ArticleDto;
import com.sanedge.newspaginate.dto.source.SourceBaseDto;

@Component
public class ArticleConverter {
    public ArticleDto toDto(Article article) {
        return new ArticleDto(article.getId(), article.getTitle(), article.getDescription(), article.getUrl(),
                article.getUrlToImage(), article.getContent(), article.getPublishedAt(), article.getPublished(),
                toDtoAuthorBase(article.getAuthor()), toDtoSourceBase(article.getSource()));

    }

    public ArticleBaseDto toDtoArticleBase(Article article) {
        return new ArticleBaseDto(article.getId(), article.getTitle(), article.getDescription(), article.getUrl(),
                article.getUrlToImage(), article.getContent(), article.getPublishedAt(), article.getPublished());
    }

    private AuthorBaseDto toDtoAuthorBase(Author author) {
        return new AuthorBaseDto(author.getName(), author.getLastname(), author.getCreateAt());
    }

    private SourceBaseDto toDtoSourceBase(Source source) {
        return new SourceBaseDto(source.getId(), source.getName(), source.getCode(), source.getCreateAt());
    }

}
