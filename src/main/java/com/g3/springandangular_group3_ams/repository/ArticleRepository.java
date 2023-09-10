package com.g3.springandangular_group3_ams.repository;

import com.g3.springandangular_group3_ams.model.entity.Article;
import com.g3.springandangular_group3_ams.model.entity.UserApp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ArticleRepository extends JpaRepository<Article, UUID> {

    Page<Article> findByBookMarkedByUsersContaining(UserApp userApp, PageRequest pageRequest);



}
