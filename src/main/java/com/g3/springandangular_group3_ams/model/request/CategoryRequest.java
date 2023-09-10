package com.g3.springandangular_group3_ams.model.request;

import com.g3.springandangular_group3_ams.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CategoryRequest {
    private String name;

    public Category toEntity(){
        return new Category( this.name);
    }
    public Category toEntityById(Long id){
        return new Category(id,this.name);
    }

}
