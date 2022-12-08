package com.sanedge.newspaginate.dto.source;

import java.time.LocalDate;
import java.util.List;

import com.sanedge.newspaginate.dto.article.ArticleBaseDto;

public class SourceDto extends SourceBaseDto {
    private List<ArticleBaseDto> articles;

    public SourceDto(Integer id, String name, String code, LocalDate createAt, List<ArticleBaseDto> articles) {
        super(id, name, code, createAt);
        this.articles = articles;
    }

    public List<ArticleBaseDto> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleBaseDto> articles) {
        this.articles = articles;
    }

}
