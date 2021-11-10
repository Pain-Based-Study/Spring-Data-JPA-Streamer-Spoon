package com.rebwon.cases;

import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final EntityManager em;

    @Transactional
    public Member addMember(String username) {
        Member member = new Member(username);
        return memberRepository.save(member);
    }

    @Transactional
    public Member updateMember(Long memberId, String username) {
        Member member = memberRepository.findById(memberId).get();
        if(memberRepository.findByUsername(username) == null) {
            member.setUsername(username);
        }
        return member;
    }
}
