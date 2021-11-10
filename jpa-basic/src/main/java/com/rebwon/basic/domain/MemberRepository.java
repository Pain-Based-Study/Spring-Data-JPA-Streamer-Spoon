package com.rebwon.basic.domain;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Collection<MemberDto> findByNickname(String nickname);
}
