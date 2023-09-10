package com.g3.springandangular_group3_ams.repository;

import com.g3.springandangular_group3_ams.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByNameContains(String name);

    Category findByName(String name);


}
