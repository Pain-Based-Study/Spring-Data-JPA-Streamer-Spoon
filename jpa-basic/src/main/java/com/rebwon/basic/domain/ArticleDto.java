package com.rebwon.basic.domain;

import lombok.Value;

@Value
public class ArticleDto {

    private final Long id;
    private final String title;
    private final String content;
    private final Long memberId;
}
