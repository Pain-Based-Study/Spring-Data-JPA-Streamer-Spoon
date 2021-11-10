package com.rebwon.streamer.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment",
    indexes = {
        @Index(name = "idx_author_comment", columnList = "account_id"),
        @Index(name = "idx_article_comment", columnList = "article_id")
    })
public class Comment {

    protected Comment() {}

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id",
        foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id",
        foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Article article;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    @Column(nullable = true)
    private boolean deleted = false;

    public Comment(String content, Account account, Article article) {
        this.content = content;
        this.account = account;
        this.article = article;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Account getAccount() {
        return account;
    }

    public Article getArticle() {
        return article;
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
