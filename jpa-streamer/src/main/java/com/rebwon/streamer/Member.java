package com.rebwon.streamer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "members",
    indexes = @Index(name = "idx_nickname_member", columnList = "nickname"))
public class Member {

    protected Member() {}

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private boolean deleted = false;

    public Member(String nickname, String image) {
        this.nickname = nickname;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getImage() {
        return image;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
