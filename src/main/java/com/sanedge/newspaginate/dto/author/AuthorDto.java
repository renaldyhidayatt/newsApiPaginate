package com.sanedge.newspaginate.dto.author;

import java.time.LocalDate;
import java.util.List;

import com.sanedge.newspaginate.dto.article.ArticleBaseDto;

public class AuthorDto extends AuthorBaseDto {
    private String fullName;

    private List<ArticleBaseDto> articles;

    public AuthorDto(String name, String lastname, LocalDate createAt, String fullName,
            List<ArticleBaseDto> articleBaseDtos) {
        super(name, lastname, createAt);
        this.fullName = fullName;
        this.articles = articleBaseDtos;
    }

    public AuthorDto(String fullName, List<ArticleBaseDto> articlesdto) {
        this.fullName = fullName;
        this.articles = articlesdto;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<ArticleBaseDto> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleBaseDto> articles) {
        this.articles = articles;
    }
}
