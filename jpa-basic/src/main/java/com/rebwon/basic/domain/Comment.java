package com.rebwon.basic.domain;

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
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Entity
@Table(name = "comment",
    indexes = {
        @Index(name = "idx_author_comment", columnList = "member_id"),
        @Index(name = "idx_article_comment", columnList = "article_id")
    })
@EqualsAndHashCode(of = "id")
public class Comment {

    protected Comment() {}

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",
        foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id",
        foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Article article;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    @Column(nullable = false)
    private boolean deleted = false;

    public Comment(String content, Member member, Article article, LocalDateTime createdAt) {
        this.content = content;
        this.member = member;
        this.article = article;
        this.createdAt = LocalDateTime.now();
    }
}
