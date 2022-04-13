package com.example.contactsservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequest extends RuntimeException {
    public BadRequest(String s) {
        super(s);
    }

    public BadRequest() {
    }
}