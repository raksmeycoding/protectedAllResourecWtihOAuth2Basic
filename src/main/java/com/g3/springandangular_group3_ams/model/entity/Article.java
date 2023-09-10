package com.g3.springandangular_group3_ams.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.g3.springandangular_group3_ams.model.dto.ArticleDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "article")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserApp user;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "article_id", referencedColumnName = "id", nullable = false)
    private List<Comment> comments;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "article_category",
            joinColumns = {
                    @JoinColumn(name = "article_id", referencedColumnName = "id"),
            }
            ,

            inverseJoinColumns = {
                    @JoinColumn(name = "category_id", referencedColumnName = "id")
            })
    private List<Category> categories;


    @ManyToMany(mappedBy = "bookmarks")
    @JsonBackReference
    private List<UserApp> bookMarkedByUsers;

//    @Temporal(value = TemporalType.DATE)
    private Boolean published;



    public ArticleDto toArticleDto () {
        return ArticleDto.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
//                .user(this.user.toDto())
//                .comments(this.comments)
//                .categories(this.categories)
                .published(this.published)
                .build();
    }

    public ArticleDto toArticleDto2 () {
        return ArticleDto.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .user(this.user.toDto())
                .comments(this.comments)
                .categories(this.categories)
                .published(this.published)
                .build();
    }

    public Article toEntityOnyId() {
        return Article.builder()
                .id(this.id)
                .build();
    }


}
