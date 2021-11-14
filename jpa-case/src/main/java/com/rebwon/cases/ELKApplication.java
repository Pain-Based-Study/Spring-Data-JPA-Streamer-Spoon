package com.rebwon.cases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class ELKApplication {

    public static void main(String[] args) {
        SpringApplication.run(ELKApplication.class, args);
    }

//    @Component
//    static class AppRunner implements ApplicationRunner {
//        @Autowired
//        MemberRepository repository;
//
//
//        @Override
//        @Transactional
//        public void run(ApplicationArguments args) throws Exception {
//            repository.save(new Member("james"));
//        }
//    }
}
