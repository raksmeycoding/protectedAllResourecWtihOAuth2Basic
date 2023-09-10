package com.g3.springandangular_group3_ams.repository;


import com.g3.springandangular_group3_ams.model.entity.Article;
import com.g3.springandangular_group3_ams.model.entity.UserApp;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserApp, UUID> {


    @Modifying
    @Query(value = "DELETE FROM bookmark b WHERE b.user_id = :userId AND b.article_id = :articleId", nativeQuery = true)
    void deleteUserBookmark(@Param("userId") UUID userId, @Param("articleId") UUID articleId);


}
