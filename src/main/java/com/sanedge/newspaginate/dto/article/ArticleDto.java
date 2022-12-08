package com.sanedge.newspaginate.dto.article;

import java.time.LocalDate;

import com.sanedge.newspaginate.dto.author.AuthorBaseDto;
import com.sanedge.newspaginate.dto.source.SourceBaseDto;

public class ArticleDto extends ArticleBaseDto {
    private AuthorBaseDto author;
    private SourceBaseDto source;

    public ArticleDto(Integer id, String title, String description, String url, String urlToImage, String content,
            LocalDate publishedAt, Boolean published, AuthorBaseDto author, SourceBaseDto source) {

        super(id, title, description, url, urlToImage, content, publishedAt, published);
        this.author = author;
        this.source = source;
    }

    public AuthorBaseDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBaseDto author) {
        this.author = author;
    }

    public SourceBaseDto getSource() {
        return source;
    }

    public void setSource(SourceBaseDto source) {
        this.source = source;
    }

}
