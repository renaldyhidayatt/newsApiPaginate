package com.sanedge.newspaginate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sanedge.newspaginate.domain.Article;
import com.sanedge.newspaginate.dto.article.ArticleBaseDto;
import com.sanedge.newspaginate.services.ArticleService;
import com.sanedge.newspaginate.utils.PageArticle;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Validated
@RestController
@RequestMapping("/article")
@CrossOrigin("*")
public class ArticleController {
    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping()
    public ResponseEntity<ArticleBaseDto> createArticles(@RequestBody @Valid Article article) {
        ArticleBaseDto articleBaseDTO = articleService.createArticle(article);
        return new ResponseEntity<ArticleBaseDto>(articleBaseDTO, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<PageArticle> getAllArticles(
            @RequestParam(required = false, defaultValue = "1") @Valid @Positive int page,
            @RequestParam(required = false, defaultValue = "true") Boolean published,
            @RequestParam(required = false) @Valid @Size(min = 3, max = 10) String query

    ) {
        PageArticle pageArticle = articleService.getAllArticle(page, published, query);

        return new ResponseEntity<>(pageArticle, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticles(@PathVariable Integer id) {
        return new ResponseEntity<>(articleService.deleteArticle(id), HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<ArticleBaseDto> updateArticle(@PathVariable Integer id, @RequestBody @Valid Article article) {
        ArticleBaseDto articleBaseDTO = articleService.updateArticle(id, article);
        return new ResponseEntity<ArticleBaseDto>(articleBaseDTO, HttpStatus.CREATED);

    }
}
