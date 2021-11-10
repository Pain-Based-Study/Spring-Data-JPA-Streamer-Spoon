package com.rebwon.mapping;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Member {

    protected Member() {}

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private Locker locker;

    public Member(String username) {
        this.username = username;
    }
}
