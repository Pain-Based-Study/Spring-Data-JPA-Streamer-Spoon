package com.rebwon.basic.domain;

import java.time.LocalDateTime;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    <T> Collection<T> findByCreatedAtAfter(LocalDateTime createdAt, Class<T> type);

    Collection<ArticleIdAndMemberIdOnly> findByTitle(String title);
}
