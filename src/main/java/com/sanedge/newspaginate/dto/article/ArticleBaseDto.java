package com.sanedge.newspaginate.dto.article;

import java.time.LocalDate;

public class ArticleBaseDto {
    Integer id;
    private String title;
    private String description;
    private String url;

    private String urlToImage;

    private String content;
    private LocalDate publishedAt;
    private Boolean published;

    public ArticleBaseDto(Integer id, String title, String description, String url, String urlToImage, String content,
            LocalDate publishedAt, Boolean published) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.content = content;
        this.publishedAt = publishedAt;
        this.published = published;
    }

    public ArticleBaseDto() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDate publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

}
