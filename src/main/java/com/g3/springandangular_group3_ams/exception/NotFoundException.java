package com.g3.springandangular_group3_ams.exception;

public class NotFoundException extends RuntimeException {
    public String title;

    public NotFoundException(String title, String message) {
        super(message);
        this.title = title;
    }
}
