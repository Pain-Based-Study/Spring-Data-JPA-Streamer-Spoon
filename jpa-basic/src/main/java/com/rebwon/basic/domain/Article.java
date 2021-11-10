package com.rebwon.basic.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Entity
@Table(name = "article",
    indexes = {
        @Index(name = "idx_title_article", columnList = "title"),
        @Index(name = "idx_author_article", columnList = "member_id")
    })
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(of = "id")
public class Article {

    protected Article() {}

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",
        foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    @Column(nullable = false)
    private boolean deleted = false;
    @Enumerated(EnumType.STRING)
    private ArticleType type;
    @OneToMany
    private Set<Tag> tags = new HashSet<>();
    @OneToMany(mappedBy = "article")
    private List<Comment> comments = new ArrayList<>();

    public Article(String title, String content, Member member, Set<Tag> tags) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.createdAt = LocalDateTime.now();
        this.type = ArticleType.DRAFTED;
        this.tags.addAll(tags);
    }

    public void published() {
        this.type = ArticleType.PUBLISHED;
        this.modifiedAt = LocalDateTime.now();
    }
}
