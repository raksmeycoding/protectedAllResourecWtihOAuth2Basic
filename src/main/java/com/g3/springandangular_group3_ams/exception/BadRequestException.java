package com.g3.springandangular_group3_ams.exception;

public class BadRequestException extends RuntimeException {
    public String title;

    public BadRequestException(String title, String message) {
        super(message);
        this.title = title;
    }
}
