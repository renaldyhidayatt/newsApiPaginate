package com.sanedge.newspaginate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.sanedge.newspaginate.domain.Source;
import com.sanedge.newspaginate.dto.source.SourceBaseDto;
import com.sanedge.newspaginate.services.SourceService;
import com.sanedge.newspaginate.utils.PageSource;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Validated
@RestController
@RequestMapping("/source")
public class SourceController {

    private SourceService sourceService;

    @Autowired
    public SourceController(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @PostMapping
    public ResponseEntity<SourceBaseDto> createSource(@RequestBody @Valid Source source) {
        return new ResponseEntity<>(sourceService.createSource(source), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<SourceBaseDto> updatesSources(@PathVariable Integer id, @RequestBody @Valid Source source) {
        SourceBaseDto sourceUpdate = sourceService.updateSource(id, source);
        return new ResponseEntity<>(sourceUpdate, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSources(@PathVariable Integer id) {
        return new ResponseEntity<>(sourceService.deleteSource(id), HttpStatus.NOT_FOUND);
    }

    @GetMapping()
    public ResponseEntity<PageSource> getAllSources(
            @RequestParam(required = false, defaultValue = "1") @Valid @Positive int page,
            @RequestParam(required = false) @Valid @Size(min = 3, max = 10) String q) {
        PageSource sourceDTOS = sourceService.getAllSource(page, q);
        return new ResponseEntity<>(sourceDTOS, HttpStatus.OK);
    }

}