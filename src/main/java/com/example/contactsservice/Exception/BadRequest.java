package com.example.contactsservice.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is a generic Bad Request (Status code: 400) exception that could be raised by any Controller. In case you are looking for any specific
 * not found exception then extend this class.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequest extends RuntimeException {

    public BadRequest(String s) {
        super(s);
    }

    public BadRequest() {
    }
}
