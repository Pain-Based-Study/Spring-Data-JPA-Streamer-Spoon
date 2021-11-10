package com.rebwon.streamer;

import static com.speedment.jpastreamer.streamconfiguration.StreamConfiguration.of;

import com.rebwon.streamer.domain.Account$;
import com.rebwon.streamer.domain.Article;
import com.rebwon.streamer.domain.Account;
import com.rebwon.streamer.domain.Article$;
import com.speedment.jpastreamer.application.JPAStreamer;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.JoinType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class StreamerApplicationTest {

    @Autowired
    EntityManagerFactory emf;

    JPAStreamer streamer;

    @BeforeEach
    void setUp() {
        streamer = JPAStreamer.of(emf);
    }

    @Test
    void name() {

    }

    @Test
    @DisplayName("게시글 엔터티와 멤버 엔터티를 Inner Join으로 가져온다.")
    void article_member_inner_join() {
        List<Article> articles = streamer.stream(of(Article.class)
                .joining(Article$.account, JoinType.INNER))
            .collect(Collectors.toList());
    }

    @Test
    @DisplayName("게시글 엔터티와 멤버 엔터티를 Inner Join하고 Tag엔터티는 LEFT JOIN으로 가져온다.")
    void article_member_inner_join_left_join_tags() {
        List<Article> articles = streamer.stream(of(Article.class)
                .joining(Article$.account, JoinType.INNER)
                .joining(Article$.tags))
            .collect(Collectors.toList());
    }

    @Test
    @DisplayName("닉네임이 일치하는 계정 엔터티 조회")
    void asdas() {
        streamer.stream(Account.class)
            .filter(Account$.nickname.equal("rebwon"))
            .findFirst();
    }

    @Test
    @DisplayName("닉네임이 R로 시작하면서, 탈퇴하지 않은 유저 조회")
    void asdasdq() {
        streamer.stream(Account.class)
            .filter(Account$.nickname.startsWith("R")
                .and(Account$.deleted.isFalse()))
            .findFirst();
    }
}