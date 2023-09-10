package com.g3.springandangular_group3_ams.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public interface BookmarkService {

    ResponseEntity<?> insertBookmarkWithUserId(UUID userId, UUID articleId);


    ResponseEntity<?> getAllBookmarkedArticleWithUser(UUID userId, Integer pageNumber, Integer PageSize);

    ResponseEntity<?> deleteBookMarkedArticleWitUserAndArticleId(@PathVariable UUID userId, @RequestParam UUID articleId);


}
