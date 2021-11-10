package com.rebwon.streamer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "account",
    indexes = @Index(name = "idx_nickname_account", columnList = "nickname"))
public class Account {

    protected Account() {}

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private boolean deleted = false;
    @Column(nullable = false)
    private int age;

    public Account(String nickname, String image) {
        this.nickname = nickname;
        this.image = image;
    }

    public int getAge() {
        return age;
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
