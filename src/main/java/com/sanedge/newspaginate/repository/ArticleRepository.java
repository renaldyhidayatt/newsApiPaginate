package com.sanedge.newspaginate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sanedge.newspaginate.domain.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Page<Article> findAllByPublished(Boolean published, Pageable pageable);

    @Query("SELECT p FROM Article p WHERE " +
            "p.title   LIKE CONCAT('%',:query, '%')" +
            "Or p.description LIKE CONCAT('%', :query, '%')" +
            "Or p.content LIKE CONCAT('%', :query, '%')")
    Page<Article> searchArticle(String query, Pageable pageable);
}
