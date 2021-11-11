package com.rebwon.cases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public Member addMember(String username) {
        Member member = new Member(username);
        return memberRepository.save(member);
    }

    @Transactional
    public void addMember2(String username) {
        Member nullMember = em.find(Member.class, 1L);
        log.info("Entity is null : {}");
        Member member = new Member(username);
        memberRepository.save(member);
        TypedQuery<Member> query = em.createQuery(
                "select m from Member m where m.username = :username", Member.class)
            .setParameter("username", "rebwon");
        Member sameMember = query.getSingleResult();
        log.info("Same Member?? : {}", member == sameMember);
        throw new IllegalStateException();
    }

    @Transactional
    public void addMember1(String username) {
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
        //actual = em.find(Member.class, member.getId());
//        actual = jdbcTemplate.queryForObject("SELECT * FROM member where id = ?", new RowMapper<Member>() {
//                @Override
//                public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//                    Member dbMember = new Member();
//                    dbMember.setId(rs.getLong("id"));
//                    dbMember.setUsername(rs.getString("username"));
//                    return member;
//                }
//            }, member.getId());
        actual = memberRepository.findById(member.getId()).get();
        log.info("Entity is null ? : {}", actual);
    }

    @Transactional
    public Member updateMember(Long memberId, String username) {
        Member member = memberRepository.findById(memberId).get();
        if(memberRepository.findByUsername(username) == null) {
            member.setUsername(username);
        }
        return member;
    }

    @Transactional(readOnly = true)
    public Member findById(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        Member sameMember = em.find(Member.class, memberId);
        log.info("Same Member?? : {}", member == sameMember);
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
        TypedQuery<Member> query = em.createQuery(
                "select m from Member m where m.id = :id", Member.class)
            .setParameter("id", memberId);
        Member sameMember = query.getSingleResult();
        //Member sameMember = memberRepository.getById(memberId);
        log.info("Same Member?? : {}", member == sameMember);
        return member;
    }
}
