package com.sanedge.newspaginate.services;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sanedge.newspaginate.converter.ArticleConverter;
import com.sanedge.newspaginate.converter.AuthorConverter;
import com.sanedge.newspaginate.domain.Author;
import com.sanedge.newspaginate.dto.author.AuthorBaseDto;
import com.sanedge.newspaginate.repository.AuthorRepository;
import com.sanedge.newspaginate.utils.PageAuthor;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorConverter authorConverter;

    private final ArticleConverter articleConverter;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, AuthorConverter authorConverter,
            ArticleConverter articleConverter) {
        this.authorRepository = authorRepository;
        this.authorConverter = authorConverter;
        this.articleConverter = articleConverter;
    }

    public AuthorBaseDto createAuthor(Author author) {
        Author authors = authorRepository.save(author);
        return authorConverter.toDtoAuthorBase(authors);
    }

    public AuthorBaseDto updateAuthor(Integer id, Author author) {
        Author authorSelect = authorRepository.findById(id).orElse(null);
        authorSelect.setId(id);
        authorSelect.setName(author.getName());
        authorSelect.setLastname(author.getLastname());
        Author authorUpdated = authorRepository.save(authorSelect);
        return authorConverter.toDtoAuthorBase(authorUpdated);
    }

    public String deleteAuthor(Integer id) {
        authorRepository.deleteById(id);
        return "deleted author con el id " + id;
    }

    public PageAuthor getAllAuthor(int page, LocalDate createAt, String query) {
        Pageable pageable = PageRequest.of(page - 1, 3);
        PageAuthor pageAuthor = new PageAuthor();
        if (query != null) {
            Page<Author> paged = authorRepository.searchAuthor(query, pageable);
            pageAuthor.setStatus(HttpStatus.OK);
            pageAuthor.setPage(paged.getSize());
            pageAuthor.setSize(paged.getTotalPages());
            pageAuthor.setTotalResult(paged.getTotalElements());
            pageAuthor.setContent(paged.getContent().stream().map(author -> authorConverter.toDto(author))
                    .collect(Collectors.toList()));
        } else {
            Page<Author> paged = authorRepository.findAllByCreateAtAfter(createAt, pageable);

            pageAuthor.setStatus(HttpStatus.OK);
            pageAuthor.setPage(paged.getSize());
            pageAuthor.setSize(paged.getTotalPages());
            pageAuthor.setTotalResult(paged.getTotalElements());
            pageAuthor.setContent(paged.getContent().stream().map(author -> authorConverter.toDto(author))
                    .collect(Collectors.toList()));
        }

        return pageAuthor;
    }
}
