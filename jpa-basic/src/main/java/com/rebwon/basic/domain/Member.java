package com.rebwon.basic.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "members",
    indexes = @Index(name = "idx_nickname_member", columnList = "nickname"))
@EqualsAndHashCode(of = "id")
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
}
