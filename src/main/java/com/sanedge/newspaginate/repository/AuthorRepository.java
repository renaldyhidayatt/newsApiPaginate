package com.sanedge.newspaginate.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sanedge.newspaginate.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Page<Author> findAllByCreateAtAfter(LocalDate created_at, Pageable pageable);

    @Query("SELECT a FROM Author a WHERE " +
            "a.name LIKE CONCAT('%',:query, '%')" +
            "Or a.lastname LIKE CONCAT('%', :query, '%')")
    Page<Author> searchAuthor(String query, Pageable pageable);
}
