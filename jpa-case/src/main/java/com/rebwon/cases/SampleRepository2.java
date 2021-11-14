package com.rebwon.cases;

import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@Repository
@Transactional//(propagation = Propagation.REQUIRES_NEW)
@RequiredArgsConstructor
public class SampleRepository2 {

    private final EntityManager em;

    public void handle() {
        log.info("Current Transaction : {}", TransactionSynchronizationManager.getCurrentTransactionName());
        log.info("EntityManager Reference : {}", em);
        try {
            if(AopUtils.isAopProxy(em) && em instanceof Advised) {
                Object target = ((Advised)em).getTargetSource().getTarget();
                EntityManager entityManager = (EntityManager)target;
                log.info("EntityManager Reference : {}", entityManager);
            }
        } catch (Exception e) {

        }
    }
}
