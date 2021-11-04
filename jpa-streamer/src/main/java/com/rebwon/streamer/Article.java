package com.rebwon.streamer;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "article",
    indexes = {
        @Index(name = "idx_title_article", columnList = "title")
        //@Index(name = "idx_author_article", columnList = "memberId")
    })
public class Article {

    protected Article() {}

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    /*@Column(nullable = false)
    private Long memberId;*/
    @ManyToOne
    private Member member;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    @Column(nullable = false)
    private boolean deleted = false;
    @OneToMany
    private Set<Tag> tags = new HashSet<>();

    /*public Article(String title, String content, Long memberId) {
        this.title = title;
        this.content = content;
        this.memberId = memberId;
        this.createdAt = LocalDateTime.now();
    }*/

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    /*public Long getMemberId() {
        return memberId;
    }*/

    public Member getMember() {
        return member;
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

    public Set<Tag> getTags() {
        return tags;
    }
}
