package com.g3.springandangular_group3_ams.service.serviceimp;

import com.g3.springandangular_group3_ams.exception.BadRequestException;
import com.g3.springandangular_group3_ams.exception.NotFoundException;
import com.g3.springandangular_group3_ams.model.entity.Article;
import com.g3.springandangular_group3_ams.model.entity.UserApp;
import com.g3.springandangular_group3_ams.model.response.Response;
import com.g3.springandangular_group3_ams.model.response.ResponsePage;
import com.g3.springandangular_group3_ams.repository.ArticleRepository;
import com.g3.springandangular_group3_ams.repository.BookmarkRepository;
import com.g3.springandangular_group3_ams.repository.UserRepository;
import com.g3.springandangular_group3_ams.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Primary
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;

    private final ArticleRepository articleRepository;

    public ResponseEntity<?> insertBookmarkWithUserId(UUID userId, UUID articleId) {

        Article existingArticle = bookmarkRepository.findById(articleId).orElseThrow(() -> new NotFoundException("error", "Article is not found with id=" + articleId));

//        check if user is existed
        UserApp existingUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("error", "User is not found with id=" + userId));

//        check user is already bookmarked on article.
        if (bookmarkRepository.isUserBookmarkedOnArticle(userId, articleId)) {
            throw new BadRequestException("error", "User had been already bookmarked on this article.");
        }

        existingUser.getBookmarks().add(existingArticle.toEntityOnyId());
        userRepository.save(existingUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(Response.builder()
                .status("200")
                .message("Bookmarked article successfully.")
                .payload(bookmarkRepository.findById(articleId))
                .build());
    }


    @Override
    public ResponseEntity<?> getAllBookmarkedArticleWithUser(UUID userId, Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        UserApp userApp = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("error", "User is not found."));


        Page<Article> bookMarkedArticle = articleRepository.findByBookMarkedByUsersContaining(userApp, pageRequest);


//        System.out.println(bookMarkedArticle.getContent().stream().map(Article::toArticleDto).collect(Collectors.toList()));
//        System.out.println(userApp.getBookmarks().stream().map(Article::toArticleDto).collect(Collectors.toList()));


        return ResponseEntity.status(HttpStatus.OK).body(ResponsePage
                .builder()
                .status("200")
                .message("Get data successfully.")
                .page(pageNumber)
                .size(pageSize)
                .totalPage(bookMarkedArticle.getTotalPages()
                ).totalElements(bookMarkedArticle.getTotalElements())
                .payload(bookMarkedArticle.getContent().stream().map(Article::toArticleDto2).collect(Collectors.toList())).build());
    }


    public ResponseEntity<?> deleteBookMarkedArticleWitUserAndArticleId(@PathVariable UUID userId, @RequestParam UUID articleId) {
        UserApp userApp = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("error", "User is not found with id=" + userId));

//        check user is already bookmarked on article
        if(!bookmarkRepository.isUserBookmarkedOnArticle(userId, articleId)){
            throw new BadRequestException("error", "User had not been already bookmarked on this article.");

        }

        userRepository.deleteUserBookmark(userId, articleId);

        return ResponseEntity.status(HttpStatus.OK).body(Response.builder().status(HttpStatus.OK.name()).message("Delete bookmark successfully.").build());
    }
}
