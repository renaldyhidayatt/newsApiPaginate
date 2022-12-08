package com.sanedge.newspaginate.utils;

import java.util.List;
import org.springframework.http.HttpStatus;

import com.sanedge.newspaginate.dto.article.ArticleDto;

public class PageArticle {
    private HttpStatus status;
    private int page;
    private int size;
    private Long totalResult;
    private List<ArticleDto> content;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Long getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(Long totalResult) {
        this.totalResult = totalResult;
    }

    public List<ArticleDto> getContent() {
        return content;
    }

    public void setContent(List<ArticleDto> content) {
        this.content = content;
    }

}
