package com.rebwon.querydsl;

import static org.junit.jupiter.api.Assertions.*;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QueryDslApplicationTest {

    @Autowired
    JPAQueryFactory queryFactory;

    @Test
    void name() {
        QMember member = QMember.member;
        queryFactory
            .selectFrom(member)
            .where(member.name.eq("rebwon"))
            .fetchOne();
    }
}