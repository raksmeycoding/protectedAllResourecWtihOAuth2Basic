package com.g3.springandangular_group3_ams.model.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.g3.springandangular_group3_ams.model.dto.CategoryDto;
import com.g3.springandangular_group3_ams.model.entity.Article;
import com.g3.springandangular_group3_ams.model.entity.Category;
import com.g3.springandangular_group3_ams.model.entity.UserApp;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequest {
    private String title;
    private String description;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<Category> categories;
    private UUID teacherId;
    private Boolean published = true;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Category {
        private String name;

        public com.g3.springandangular_group3_ams.model.entity.Category toCategory() {
            return new com.g3.springandangular_group3_ams.model.entity.Category(null, this.name);
        }
    }





    public  Article toArticleEntity() {
        return Article.builder()
                .id(null)
                .title(this.title)
                .description(this.description)
                .published(this.published)
                .build();
    }
}
