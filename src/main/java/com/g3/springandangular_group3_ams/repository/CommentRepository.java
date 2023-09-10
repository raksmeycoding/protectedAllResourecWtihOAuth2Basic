package com.g3.springandangular_group3_ams.repository;

import com.g3.springandangular_group3_ams.model.entity.Comment;
import com.g3.springandangular_group3_ams.model.request.CommentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(nativeQuery = true, value = "insert into comment(article_id, id, caption) values (?1, ?2, ?3) returning *")
    Comment saveCommentWithArticleId(UUID article_id, UUID comment_id, String caption);
}
