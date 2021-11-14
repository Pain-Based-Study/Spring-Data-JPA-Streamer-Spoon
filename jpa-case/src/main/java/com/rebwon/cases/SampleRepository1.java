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
public class SampleRepository1 {

    private final EntityManager em;

    public void handle() {
        log.info("Current Transaction : {}", TransactionSynchronizationManager.getCurrentTransactionName());
        log.info("EntityManager Reference : {}", em);
        try {
            EntityManager entityManager = (EntityManager) ((Advised) em).getTargetSource().getTarget();
            log.info("ENTITYMANAGER : {}", entityManager);
        } catch (Exception e) {

        }
    }
}
