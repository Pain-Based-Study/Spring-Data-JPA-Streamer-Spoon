package com.rebwon.streamer;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "comment",
    indexes = {
        @Index(name = "idx_author_comment", columnList = "memberId"),
        @Index(name = "idx_article_comment", columnList = "articleId")
    })
public class Comment {

    protected Comment() {}

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Long memberId;
    @Column(nullable = false)
    private Long articleId;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    @Column(nullable = true)
    private boolean deleted = false;

    public Comment(String content, Long memberId, Long articleId) {
        this.content = content;
        this.memberId = memberId;
        this.articleId = articleId;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
