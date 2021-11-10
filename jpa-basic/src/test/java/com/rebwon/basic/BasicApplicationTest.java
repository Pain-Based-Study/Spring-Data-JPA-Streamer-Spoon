package com.rebwon.basic;

import static org.assertj.core.api.Assertions.assertThat;

import com.rebwon.basic.domain.Article;
import com.rebwon.basic.domain.Book;
import com.rebwon.basic.domain.Member;
import com.rebwon.basic.domain.Tag;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@Transactional
class BasicApplicationTest {

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("지연 로드를 발생시키는 조건은 프록시 객체의 메서드 중 하나가 반드시"
        + "호출돼야 데이터베이스에서 로드한다.")
    @DirtiesContext
    void correctly_behavior_entityManager_lazy_loading() {
        // Arrange
        generateData();

        // Act
        Article article = em.find(Article.class, 1L);

        // Assert
        assertThat(article.getMember().getNickname()).isEqualTo("rebwon");
    }

    @Test
    @DisplayName("프록시 객체의 메서드를 호출하지 않으면, 지연 로드가 발동하지 않는다.")
    @DirtiesContext
    void not_correctly_behavior_entityManager_lazy_loading() {
        // Arrange
        generateData();

        // Act
        Article article = em.find(Article.class, 1L);

        // Assert
        assertThat(article.getMember()).isNotNull();
    }

    @Test
    @DisplayName("컬렉션 매핑에서 지연로드가 발생하지 않는 조건은 단순히 getTags()와 같은"
        + "getter를 통한 프록시 객체의 not null 여부를 검증할 때이다.")
    @DirtiesContext
    void not_correctly_behavior_entityManager_oneToMany_lazy_loading() {
        // Arrange
        generateData();

        // Act
        Article article = em.find(Article.class, 1L);

        // Assert
        assertThat(article.getTags()).isNotNull();
    }

    @Test
    @DisplayName("컬렉션 매핑에서 컬렉션 내부의 값을 확인하는 메서드, 혹은 컬렉션을 순회하는 경우"
        + "지연 로드가 동작하여 데이터베이스에서 값을 로드한다.")
    @DirtiesContext
    void correctly_behavior_entityManager_oneToMany_lazy_loading() {
        // Arrange
        generateData();

        // Act
        Article article = em.find(Article.class, 1L);

        // Assert
        //assertThat(article.getTags().size()).isEqualTo(3);
        assertThat(article.getTags().isEmpty()).isFalse();
        //article.getTags().forEach(System.out::println);
    }

    protected void generateData() {
        Book book = new Book("자바 ORM 표준 JPA 프로그래밍");
        em.persist(book);
        Member member = new Member("rebwon", "temp.png");
        em.persist(member);
        Set<Tag> tags = Set.of(new Tag("Spring"), new Tag("Hibernate"), new Tag("POEAA"));
        for (Tag t : tags) {
            em.persist(t);
        }
        Article article = new Article("JPA 탐구해보기", "POEAA 책을 살펴보세요", member, tags);
        em.persist(article);
        em.flush();
        em.clear();
    }
}