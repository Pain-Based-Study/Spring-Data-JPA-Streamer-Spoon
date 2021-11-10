package com.rebwon.mapping;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team2 {

    protected Team2(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "team2")
    private List<Member2> members = new ArrayList<>();

    public Team2(String name) {
        this.name = name;
    }

    public void addMember(Member2 member2) {
        this.members.add(member2);
        member2.setTeam2(this);
    }
}
