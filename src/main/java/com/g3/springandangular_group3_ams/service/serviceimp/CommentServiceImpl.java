package com.g3.springandangular_group3_ams.service.serviceimp;

import com.g3.springandangular_group3_ams.model.entity.Comment;
import com.g3.springandangular_group3_ams.model.request.CommentRequest;
import com.g3.springandangular_group3_ams.repository.ArticleRepository;
import com.g3.springandangular_group3_ams.repository.CommentRepository;
import com.g3.springandangular_group3_ams.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Primary
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public ResponseEntity<?> addCommentToArticle(UUID articleId, CommentRequest commentRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> getCommentByArticleId(UUID article_id) {
        return null;
    }
}
