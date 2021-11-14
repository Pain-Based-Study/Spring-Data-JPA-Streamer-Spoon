package com.rebwon.cases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final EntityManager em;
    private final EntityManagerFactory emf;
    private final SampleRepository1 sampleRepository1;
    private final SampleRepository2 sampleRepository2;
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void addMember1(String username) {
        Member newMember = new Member(username);
        em.persist(newMember);
        em.createQuery("select a from Article a").getResultList();
        em.createQuery("select m from Member m").getResultList();
    }

    @Transactional
    public void addMembers(String username) {
        Member member = em.find(Member.class, 1L);
        member.setUsername(username);
        em.persist(member);
        em.remove(member);
    }

    @Transactional
    public void addMember(String username) {
        // create
        Member member = new Member(username);
        memberRepository.save(member);

        // read
        Member actual = memberRepository.findById(member.getId()).get();
        log.info("Same Entity ? : {}", member == actual);

        // update
        String newUsername = "kitty";
        member.setUsername(newUsername);
        memberRepository.save(member);

        // update verify
        actual = memberRepository.findById(member.getId()).get();
        log.info("Same Username ? : {}", member.getUsername().equals(actual.getUsername()));

        // delete
        memberRepository.delete(actual);
        actual = memberRepository.findById(member.getId()).orElse(null);
        log.info("Entity is null ? : {}", actual);
    }


//    actual = jdbcTemplate.queryForObject("SELECT * FROM member where id = ?", (rs, rowNum) -> {
//        Member dbMember = new Member();
//        dbMember.setId(rs.getLong("id"));
//        dbMember.setUsername(rs.getString("username"));
//        return dbMember;
//    }, member.getId());
//            actual = em.createQuery("select m from Member m where m.id = :id", Member.class)
//            .setParameter("id", member.getId())
//            .setFlushMode(FlushModeType.COMMIT)
//            .getSingleResult();
//    actual = jdbcTemplate.queryForObject("SELECT * FROM member where id = ?", (rs, rowNum) -> {
//        Member dbMember = new Member();
//        dbMember.setId(rs.getLong("id"));
//        dbMember.setUsername(rs.getString("username"));
//        return member;
//    }, member.getId());


    //actual = em.find(Member.class, member.getId());

    @Transactional
    public void updateMember1(Long memberId, String username) {
        Member member = new Member(username);

        Member updateMember = memberRepository.findById(memberId).get();
        log.info("Same Identity Member : {}", member == updateMember);
    }

    @Transactional(readOnly = true)
    public Member findById(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        Member sameMember = memberRepository.findById(memberId).get();
        log.info("Same Entity : {}", member == sameMember);
        return member;
    }

    @Transactional(readOnly = true)
    public Member findById1(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        TypedQuery<Member> query = em.createQuery(
                "select m from Member m where m.id = :id AND m.username = :username", Member.class)
            .setParameter("id", memberId)
            .setParameter("username", "rebwon");
        Member sameMember = query.getSingleResult();
        log.info("Same Member?? : {}", member == sameMember);
        return member;
    }

    @Transactional(readOnly = true)
    public Member findById2(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        Member sameMember = memberRepository.getById(memberId);
//        TypedQuery<Member> query = em.createQuery(
//                "select m from Member m where m.id = :id", Member.class)
//            .setParameter("id", memberId);
//        Member sameMember = query.getSingleResult();
        log.info("Same Member?? : {}", member == sameMember);
        return member;
    }

    @Transactional
    public void handle() {
        sampleRepository1.handle();
        sampleRepository2.handle();
    }
}
