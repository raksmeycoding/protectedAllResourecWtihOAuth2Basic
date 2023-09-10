package com.g3.springandangular_group3_ams.service;

import com.g3.springandangular_group3_ams.model.dto.ArticleDto;
import com.g3.springandangular_group3_ams.model.entity.Article;
import com.g3.springandangular_group3_ams.model.request.ArticleRequest;
import com.g3.springandangular_group3_ams.model.request.CommentRequest;
import com.g3.springandangular_group3_ams.model.response.Response;
import com.g3.springandangular_group3_ams.model.response.ResponsePage;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ArticleService extends CommentService{
    Article createArticle(ArticleRequest articleRequest);

    Page<Article> getAllArticle(Integer pageNum, Integer pageSize);


    Article getArticleById(UUID articleId);



    ResponseEntity<Response<?>> deleteArticleById(UUID articleId);

    ResponseEntity<ArticleDto> updateArticleById(UUID articleId, ArticleRequest articleRequest);
    ResponseEntity<?> addCommentToArticle(UUID articleId, CommentRequest commentRequest);



}
