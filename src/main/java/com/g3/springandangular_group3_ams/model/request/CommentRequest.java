package com.g3.springandangular_group3_ams.model.request;


import com.g3.springandangular_group3_ams.model.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CommentRequest {
    private String caption;


    public Comment toEntity() {
        return new Comment(null, this.caption);
    }
}
