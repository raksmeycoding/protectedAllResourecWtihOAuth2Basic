package com.g3.springandangular_group3_ams.controller;


import com.g3.springandangular_group3_ams.model.dto.ArticleDto;
import com.g3.springandangular_group3_ams.model.entity.Article;
import com.g3.springandangular_group3_ams.model.request.ArticleRequest;
import com.g3.springandangular_group3_ams.model.request.CommentRequest;
import com.g3.springandangular_group3_ams.model.response.Response;
import com.g3.springandangular_group3_ams.model.response.ResponsePage;
import com.g3.springandangular_group3_ams.service.ArticleService;
import com.g3.springandangular_group3_ams.utils.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;



    @PostMapping
    public ResponseEntity<?> postArticle(@RequestBody ArticleRequest articleRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(articleService.createArticle(articleRequest)
        );
    }


    @GetMapping
    ResponseEntity<ResponsePage<?>> getAllArticle(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "3") Integer pageSize) {

        Validation.validatePagination(pageNo, pageSize);

        Page<Article> articlePage = articleService.getAllArticle(pageNo - 1, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponsePage.builder()
                        .message("Get article successfully.")
                        .status(HttpStatus.OK.name())
                        .payload(articlePage.getContent())
                        .page(pageNo)
                        .size(pageSize)
                        .totalElements(articlePage.getTotalElements())
                        .totalPage(articlePage.getTotalPages())
                        .build()
        );
    }


    @GetMapping("{articleId}")
    public ResponseEntity<Article> findArticleById(@PathVariable UUID articleId) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.getArticleById(articleId));
    }


    @DeleteMapping("{articleId}")
    public ResponseEntity<Response<?>> deleteArticleById(@PathVariable UUID articleId) {
        return articleService.deleteArticleById(articleId);
    }


    @PutMapping("{articleId}")
    public ResponseEntity<ArticleDto> updateArticle(@PathVariable UUID articleId, @RequestBody ArticleRequest articleRequest) {
        return articleService.updateArticleById(articleId, articleRequest);
    }


    @PostMapping("{articleId}/comments")
    public ResponseEntity<?> addCommentToArticle(@PathVariable UUID articleId, @RequestBody CommentRequest commentRequest) {
        return articleService.addCommentToArticle(articleId, commentRequest);
    }


    @GetMapping("{articleId}/comments")

    public ResponseEntity<?> getCommentByArticleId(@PathVariable UUID articleId) {
        return articleService.getCommentByArticleId(articleId);
    }


    @GetMapping("/published")
    public ResponseEntity<?> getArticleWithPublishDate(@RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "10") Integer pageSize) {
        Validation.validatePagination(pageNumber, pageSize);
        Page<Article> articlePage = articleService.getAllArticle(pageNumber - 1, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(ResponsePage.builder().status("200").message("Get articles successfully.").payload(articlePage.getContent()).page(pageNumber).totalPage(articlePage.getTotalPages()).size(pageSize).totalElements(articlePage.getTotalElements()).build());
    }


}
