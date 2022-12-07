package com.mustache.hospital.domain.dto;

import com.mustache.hospital.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String contents;

    public Article toEntity(){
        return new Article(this.id, this.title, this.contents);
    }
}
