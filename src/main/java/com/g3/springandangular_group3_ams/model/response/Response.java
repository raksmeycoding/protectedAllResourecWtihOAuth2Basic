package com.g3.springandangular_group3_ams.model.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response<T> {

    private String message;
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T payload;


}
