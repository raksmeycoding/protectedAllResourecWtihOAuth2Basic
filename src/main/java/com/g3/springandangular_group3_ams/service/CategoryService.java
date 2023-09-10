package com.g3.springandangular_group3_ams.service;

import com.g3.springandangular_group3_ams.model.dto.CategoryDto;
import com.g3.springandangular_group3_ams.model.entity.Category;
import com.g3.springandangular_group3_ams.model.request.CategoryRequest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryService {
    CategoryDto addCategory(CategoryRequest categoryRequest);
    CategoryDto getCategoryById(Long id);

    CategoryDto updateCategoryById(CategoryRequest categoryRequest,Long id);
    List<CategoryDto> getAllCategories(Integer pangeNum, Integer pageSize);
    //    CategoryDto deleteCateById(Long id);
    List<CategoryDto> searchByName(String name);
    void DeleteCategory(Long id);

    Category findByName(String name);





}
