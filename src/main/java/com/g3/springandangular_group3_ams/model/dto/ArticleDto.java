package com.g3.springandangular_group3_ams.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.g3.springandangular_group3_ams.model.entity.Category;
import com.g3.springandangular_group3_ams.model.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleDto {
    private UUID id;


    private String title;

    private String description;

    private UserDto user;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Comment> comments;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private List<Category> categories;

    @JsonInclude(JsonInclude.Include.NON_NULL)

    private Boolean published;


}
