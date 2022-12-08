package com.sanedge.newspaginate.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sanedge.newspaginate.domain.Article;
import com.sanedge.newspaginate.domain.Source;
import com.sanedge.newspaginate.dto.article.ArticleBaseDto;
import com.sanedge.newspaginate.dto.source.SourceBaseDto;
import com.sanedge.newspaginate.dto.source.SourceDto;

import java.util.stream.Collectors;

@Component
public class SourceConverter {

    public SourceDto toDto(Source source) {
        return new SourceDto(source.getId(), source.getName(), source.getCode(), source.getCreateAt(),
                toListArticleDto(source.getArticles()));
    }

    public SourceBaseDto toSourceBaseDTO(Source source) {
        return new SourceBaseDto(source.getId(), source.getName(), source.getCode(), source.getCreateAt());
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

}
