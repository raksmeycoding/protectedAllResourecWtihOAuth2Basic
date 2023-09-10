package com.g3.springandangular_group3_ams.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail notFound(
            NotFoundException notFoundException
    ) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, notFoundException.getMessage()
        );
        problemDetail.setTitle(notFoundException.title);
        problemDetail.setType(URI.create("localhost:8080/error/"));
        return problemDetail;
    }

    @ExceptionHandler(BadRequestException.class)
    public ProblemDetail badRequest(
            BadRequestException badRequestException
    ) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, badRequestException.getMessage()
        );
        problemDetail.setTitle(badRequestException.title);
        problemDetail.setType(URI.create("localhost:8080/error/"));
        return problemDetail;
    }


}
