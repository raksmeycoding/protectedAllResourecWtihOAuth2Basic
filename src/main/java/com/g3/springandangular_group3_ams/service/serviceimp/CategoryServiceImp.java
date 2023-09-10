package com.g3.springandangular_group3_ams.service.serviceimp;

import com.g3.springandangular_group3_ams.exception.BadRequestException;
import com.g3.springandangular_group3_ams.exception.NotFoundException;
import com.g3.springandangular_group3_ams.model.dto.CategoryDto;
import com.g3.springandangular_group3_ams.model.entity.Category;
import com.g3.springandangular_group3_ams.model.request.CategoryRequest;
import com.g3.springandangular_group3_ams.repository.CategoryRepository;
import com.g3.springandangular_group3_ams.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImp implements CategoryService {
    final CategoryRepository categoryRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto addCategory(CategoryRequest categoryRequest) {
        if (categoryRequest.getName().isBlank() || categoryRequest.getName().isEmpty()) {
            throw new BadRequestException(
                    "Category name invalid",
                    "Category name can not null"
            );
        }
        var categoryEntity = categoryRequest.toEntity();
        return categoryRepository.save(categoryEntity).toDto();
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException(
                        "Category Not found..",
                        "Category not found with id :" + id
                )).toDto();
    }

    @Override
    public CategoryDto updateCategoryById(CategoryRequest categoryRequest, Long id) {
        if (categoryRequest.getName().isBlank() || categoryRequest.getName().isEmpty()) {
            throw new BadRequestException(
                    "Category name invalid",
                    "Category name can not null"
            );
        }
        getCategoryById(id);
        Category category = categoryRequest.toEntityById(id);
        return categoryRepository.save(category).toDto();
    }


    public List<CategoryDto> getAllCategories(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<CategoryDto> pageResult = categoryRepository.findAll(pageable).map(Category::toDto);
        System.out.println(pageResult);
        return pageResult.getContent();
    }

    @Override
    public List<CategoryDto> searchByName(String name) {
        return categoryRepository.findByNameContains(name).stream().map(Category::toDto).toList();
    }

    @Override
    public void DeleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }


    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }


}
