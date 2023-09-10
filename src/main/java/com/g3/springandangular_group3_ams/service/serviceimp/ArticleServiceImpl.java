package com.g3.springandangular_group3_ams.service.serviceimp;

import com.g3.springandangular_group3_ams.exception.BadRequestException;
import com.g3.springandangular_group3_ams.exception.NotFoundException;
import com.g3.springandangular_group3_ams.model.dto.ArticleDto;
import com.g3.springandangular_group3_ams.model.entity.Article;
import com.g3.springandangular_group3_ams.model.entity.Category;
import com.g3.springandangular_group3_ams.model.entity.Comment;
import com.g3.springandangular_group3_ams.model.entity.UserApp;
import com.g3.springandangular_group3_ams.model.request.ArticleRequest;
import com.g3.springandangular_group3_ams.model.request.CommentRequest;
import com.g3.springandangular_group3_ams.model.response.Response;
import com.g3.springandangular_group3_ams.repository.ArticleRepository;
import com.g3.springandangular_group3_ams.repository.CategoryRepository;
import com.g3.springandangular_group3_ams.repository.CommentRepository;
import com.g3.springandangular_group3_ams.repository.UserRepository;
import com.g3.springandangular_group3_ams.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Primary
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    //
    private final CategoryRepository categoryRepository;

    private final CommentRepository commentRepository;

    @Override
    public Article createArticle(ArticleRequest articleRequest) {

        Article toSaveArticle = articleRequest.toArticleEntity();
//        toSaveArticle.setUser(new UserApp(articleRequest.getTeacherId(), null, null));
//        toSaveArticle.setCategories(List.of(new Category(1L, "dfgsdf")));
//        return articleRepository.save(toSaveArticle);


        UserApp existingUser = userRepository.findById(articleRequest.getTeacherId()).orElse(null);


        if (existingUser != null) {

//            if user is not valid permission, will throw an exception
            checkUserPermission(existingUser.getRole());
//            else
            toSaveArticle.setUser(existingUser);

            List<Category> saveList = new ArrayList<>();

            for (Category name : articleRequest.getCategories().stream().map(ArticleRequest.Category::toCategory).collect(Collectors.toList())) {

                Category listWithId = categoryRepository.findByName(name.getName());

                if (listWithId != null) {

                    saveList.add(listWithId);

                } else {

                    throw new NotFoundException("error", "Category not found!");
                }
            }

            toSaveArticle.setCategories(saveList);

            return articleRepository.save(toSaveArticle);

        }

        throw new NotFoundException("error", "User not found!");

    }


    private void checkUserPermission(String role) {
        if (!role.equals("TEACHER")) {
            throw new BadRequestException("error", "You have no permission to create article.");
        }
    }


    @Override
    public Page<Article> getAllArticle(Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        Page<Article> articlePage = articleRepository.findAll(pageRequest);
//        List<ArticleDto> articleDtoList = articlePage.stream().map(Article::toArticleDto).collect(Collectors.toList());
        return articlePage;

    }


    @Override
    public Article getArticleById(UUID articleId) {
        var findedArticle = articleRepository.findById(articleId).orElseThrow(() -> new NotFoundException("error", "Article is not found."));
        return findedArticle;
    }


    @Override
    public ResponseEntity<Response<?>> deleteArticleById(UUID articleId) {
        var findArticleById = articleRepository.findById(articleId).orElseThrow(() -> new NotFoundException("error", "Article is not found."));
        articleRepository.deleteById(findArticleById.toArticleDto().getId());
        return ResponseEntity.status(HttpStatus.OK).body(Response.builder()
                .message("Delete article successfully.")
                .status("200")
                .build());
    }

    @Override
    public ResponseEntity<ArticleDto> updateArticleById(UUID articleId, @RequestBody ArticleRequest articleRequest) {
        Article findArticle = getArticleById(articleId);
        findArticle.setTitle(articleRequest.getTitle());
        findArticle.setDescription(articleRequest.getDescription());

        UserApp existingUser = userRepository.findById(articleRequest.getTeacherId()).orElseThrow(() -> new NotFoundException("title", "User is not found exception."));
        //            if user is not valid permission, will throw an exception
        checkUserPermission(existingUser.getRole());
//            else
        findArticle.setUser(existingUser);

        List<Category> foundCategory = new ArrayList<>();

        if (existingUser != null) {
            //            if user is not valid permission, will throw an exception
            checkUserPermission(existingUser.getRole());
//            else
            findArticle.setUser(existingUser);


//            find category
            for (Category c : articleRequest.getCategories().stream().map(ArticleRequest.Category::toCategory).collect(Collectors.toList())) {
                Category findCategory = categoryRepository.findByName(c.getName());
                if (findCategory != null) {
                    foundCategory.add(findCategory);
                } else {
                    throw new NotFoundException("error", "Category is not found.");
                }
            }
        }
        findArticle.setPublished(articleRequest.getPublished());
        findArticle.setCategories(foundCategory);
        return ResponseEntity.status(HttpStatus.OK).body(articleRepository.save(findArticle).toArticleDto());


    }


    public ResponseEntity<?> addCommentToArticle(UUID articleId, CommentRequest commentRequest) {
        Optional<Article> optionalArticle = articleRepository.findById(articleId);
        if (optionalArticle.isPresent()) {
            Comment saveComment = commentRepository.saveCommentWithArticleId(articleId, UUID.randomUUID(), commentRequest.getCaption());
            return ResponseEntity.status(HttpStatus.CREATED).body(Response.builder().status("200").payload(saveComment).message("Comment with id=" + articleId + " is added successfully.").build());
        }

        throw new NotFoundException("error", "Article is not presented");

    }


    @Override
    public ResponseEntity<?> getCommentByArticleId(UUID article_id) {
        Optional<Article> articleOptional = articleRepository.findById(article_id);
        if (articleOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(Response.builder().status(HttpStatus.CREATED.name()).message("Get comments successfully").payload(articleOptional.get().getComments()).build());
        }
        throw new NotFoundException("error", "Article with id=" + article_id + " is not found.");

    }


}
