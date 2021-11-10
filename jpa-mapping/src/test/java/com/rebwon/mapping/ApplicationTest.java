package com.rebwon.mapping;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
class ApplicationTest {

    @Autowired
    EntityManager em;

    @Test
    void ManyToOneMappingInsert() {
        Team1 team1 = new Team1("맨시티");
        em.persist(team1);

        Member1 member1 = new Member1("제주스");
        Member1 member2 = new Member1("실바");
        member1.setTeam(team1);
        member2.setTeam(team1);

        em.persist(member1);
        em.persist(member2);
    }

    @Test
    @Transactional
    void OneToManyManyToOneMappingInsert() {
        Team2 team2 = new Team2("맨시티");
        em.persist(team2);

        Member2 member1 = new Member2("제주스");
        Member2 member2 = new Member2("실바");

        team2.addMember(member1);
        team2.addMember(member2);

        em.persist(member1);
        em.persist(member2);
    }

    @Test
    void OneToOneMappingInsertExceptedLazyLoadingButActualEagerLoading() {
        Member member = new Member("rebwon");
        em.persist(member);
        Locker locker = new Locker("1234");
        locker.setMember(member);
        em.persist(locker);

        em.flush();
        em.clear();

        Member dbMember = em.find(Member.class, 1L);
        assertThat(dbMember.getLocker()).isNotNull();
    }

    @Test
    void OneToOneMappingInsertExceptedLazyLoadingActualLazyLoading() {
        Member member = new Member("rebwon");
        em.persist(member);
        Locker locker = new Locker("1234");
        locker.setMember(member);
        em.persist(locker);

        em.flush();
        em.clear();

        Locker dbLocker = em.find(Locker.class, 1L);
        assertThat(dbLocker.getMember()).isNotNull();
    }
}