package com.rebwon.mapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
public class Member2 {

    protected Member2() {}

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @ManyToOne
    @JoinColumn(name = "team2_id")
    private Team2 team2;

    public Member2(String username) {
        this.username = username;
    }

    public void setTeam2(Team2 team2) {
        this.team2 = team2;
    }
}
