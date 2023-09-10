package com.g3.springandangular_group3_ams.utils;


import com.g3.springandangular_group3_ams.exception.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class Validation {

    public static void validatePagination(Integer pageNumber, Integer pageSize) {
        if (pageNumber <= 0 || pageSize <= 0) {
            throw new BadRequestException("error", "Page or page size must not less than one.");
        }
    }
}
