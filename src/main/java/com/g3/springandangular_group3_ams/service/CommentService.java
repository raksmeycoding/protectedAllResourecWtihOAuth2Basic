package com.g3.springandangular_group3_ams.service;

import com.g3.springandangular_group3_ams.model.entity.Comment;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface CommentService {

    ResponseEntity<?> getCommentByArticleId(UUID article_id);

}
