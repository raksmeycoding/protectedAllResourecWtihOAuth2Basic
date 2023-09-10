package com.g3.springandangular_group3_ams.controller;

import com.g3.springandangular_group3_ams.model.dto.CategoryDto;
import com.g3.springandangular_group3_ams.model.request.CategoryRequest;
import com.g3.springandangular_group3_ams.model.response.Response;
import com.g3.springandangular_group3_ams.model.response.ResponsePage;
import com.g3.springandangular_group3_ams.service.CategoryService;
import com.g3.springandangular_group3_ams.utils.Validation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping("/")
    @Operation(summary = "Add category")
    public ResponseEntity<Response<?>> addCategory(@RequestBody CategoryRequest categoryRequest){
        Response<?> response = Response.<CategoryDto>builder()
                .message("Insert category successfully")
                .status("201")
                .payload(categoryService.addCategory(categoryRequest))
                .build();
        return ResponseEntity.ok().body(response);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update category by id")
    public ResponseEntity<Response<?>> updateCategory(@RequestBody CategoryRequest categoryRequest,@PathVariable Long id){
        Response<?> response = Response.<CategoryDto>builder()
                .message("update category successfully")
                .status("201")
                .payload(categoryService.updateCategoryById(categoryRequest,id))
                .build();
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get category by id")
    public ResponseEntity<Response<?>> getCategoryById(@PathVariable Long id){
        Response<?> response = Response.<CategoryDto>builder()
                .message("Get category by id successfully")
                .status("201")
                .payload(categoryService.getCategoryById(id))
                .build();
        return ResponseEntity.ok().body(response);

    }


    @GetMapping("/")
    @Operation(summary = "Get all categories")
    public ResponsePage<?> getAllCategories(@RequestParam Integer pageNum,
                                            @RequestParam Integer pageSize){
        Validation.validatePagination(pageNum, pageSize);
        var payload=categoryService.getAllCategories(pageNum,pageSize);
        return ResponsePage.<List<CategoryDto>>builder()
                .message("successfully fetched categories")
                .status(String.valueOf(HttpStatus.OK))
                .payload(payload)
                .page(pageNum)
                .size(pageSize)
                .build();
    }
    @GetMapping("/searchName")
    @Operation(summary = "Search category by id")
    public Response<?> searchName(@RequestParam String name){
        categoryService.searchByName(name);
        return Response.<List<CategoryDto>>builder()
                .message("Search category successfully")
                .status(String.valueOf(HttpStatus.OK))
                .payload(categoryService.searchByName(name))
                .build();
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category by id")
    public Response<?> deleteCategory(@PathVariable("id") Long id){
        categoryService.DeleteCategory(id);
        return Response.<CategoryDto>builder()
                .message("success to deleted by id : "+id)
                .build();
    }

}
