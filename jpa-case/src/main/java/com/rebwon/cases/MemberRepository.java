package com.rebwon.cases;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Modifying
    @Query("DELETE FROM Member m WHERE m.username = ?1")
    int deleteUsername(String username);

    Member findByUsername(String username);
}



//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE Member m SET m.username = ?2 WHERE m.id = ?1")
//    int updateUsername(Long id, String username);