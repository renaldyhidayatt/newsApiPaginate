package com.sanedge.newspaginate.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sanedge.newspaginate.domain.Author;
import com.sanedge.newspaginate.dto.author.AuthorBaseDto;
import com.sanedge.newspaginate.services.AuthorService;
import com.sanedge.newspaginate.utils.PageAuthor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Validated
@RestController
@RequestMapping("/author")
public class AuthorController {
    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping()
    public ResponseEntity<AuthorBaseDto> createAuthor1(@RequestBody @Valid Author author) {
        AuthorBaseDto authors = authorService.createAuthor(author);
        return new ResponseEntity(authors, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<AuthorBaseDto> updateAuthor(@PathVariable() Integer id, @RequestBody @Valid Author author) {
        AuthorBaseDto authorBaseDTO = authorService.updateAuthor(id, author);
        return new ResponseEntity<AuthorBaseDto>(authorBaseDTO, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Integer id) {
        return new ResponseEntity<String>(authorService.deleteAuthor(id), HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public ResponseEntity<PageAuthor> getAll(
            @RequestParam(required = false, defaultValue = "1") @Valid @Positive int page,
            @RequestParam(required = false) @Valid @Size(min = 3, max = 10) String query,
            @RequestParam(required = false, defaultValue = "2022-08-23") @Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createAt) {
        PageAuthor author = authorService.getAllAuthor(page, createAt, query);
        return new ResponseEntity<PageAuthor>(author, HttpStatus.OK);
    }
}
