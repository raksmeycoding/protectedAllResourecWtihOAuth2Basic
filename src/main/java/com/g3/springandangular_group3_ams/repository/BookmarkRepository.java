package com.g3.springandangular_group3_ams.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookmarkRepository extends ArticleRepository{


    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END " +
            "FROM bookmark " +
            "WHERE user_id = :userId AND article_id = :articleId", nativeQuery = true)
    boolean isUserBookmarkedOnArticle(@Param("userId") UUID userId, @Param("articleId") UUID articleId);

}
