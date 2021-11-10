package com.rebwon.mapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class Member1 {

    protected Member1(){}

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @ManyToOne
    @JoinColumn(name = "team1_id")
    private Team1 team;

    public Member1(String username) {
        this.username = username;
    }

    public void setTeam(Team1 team) {
        this.team = team;
    }
}
