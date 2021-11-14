package com.rebwon.cases;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Article {

    @Id @GeneratedValue
    private Long id;

    private String title;
    private String content;
}
