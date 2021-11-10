package com.rebwon.basic;

import com.rebwon.basic.domain.ArticleDto;
import com.rebwon.basic.domain.ArticleRepository;
import com.rebwon.basic.domain.ArticleType;
import com.rebwon.basic.domain.MemberRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class RepositoryTests {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("JpaRepository는 Class Dto Projection을 지원한다.")
    void jpaRepository_provide_class_dto_projection() {
        memberRepository.findByNickname("rebwon");
    }

    @Test
    @DisplayName("JpaRepository는 Interface Projection도 지원한다.")
    void jpaRepository_provide_interface_dto_projection() {
        articleRepository.findByTitle("JPA");
    }

    @Test
    @DisplayName("JpaRepository는 Dynamic Projection도 지원한다.")
    void jpaRepository_provide_dynamic_dto_projection() {
        articleRepository.findByCreatedAtAfter(LocalDateTime.now(), ArticleDto.class);
    }
}
