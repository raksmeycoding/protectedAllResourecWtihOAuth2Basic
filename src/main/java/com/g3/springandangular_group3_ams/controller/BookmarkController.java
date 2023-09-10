package com.g3.springandangular_group3_ams.controller;


import com.g3.springandangular_group3_ams.service.BookmarkService;
import com.g3.springandangular_group3_ams.utils.Validation;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookmarks/user")
public class BookmarkController {

    private final BookmarkService bookmarkService;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    private static class ArticleBookMarkRequest {
        private UUID articleId;
    }


    @PostMapping("/{userId}")
    public ResponseEntity<?> insertBookmarkWithUserId(@PathVariable UUID userId, @RequestBody ArticleBookMarkRequest bookMarkRequest) {
        return bookmarkService.insertBookmarkWithUserId(userId, bookMarkRequest.getArticleId());
    }


    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllBookmarkedArticleWithUser(@PathVariable UUID userId, @RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "3") Integer pageSize) {
        Validation.validatePagination(pageNumber, pageSize);
        return bookmarkService.getAllBookmarkedArticleWithUser(userId, pageNumber - 1, pageSize);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteBookmarkedWithUserAndArticle(@PathVariable UUID userId, @RequestParam UUID articleId) {
        return bookmarkService.deleteBookMarkedArticleWitUserAndArticleId(userId, articleId);
    }


}
