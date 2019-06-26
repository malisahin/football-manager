package com.works.sharedlibrary.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author mali.sahin
 * @since 2019-06-27.
 */
@ResponseStatus(HttpStatus.PRECONDITION_REQUIRED)
public class InvalidFieldException extends RuntimeException {

    public InvalidFieldException(String message) {
        super(message);
    }

    public InvalidFieldException(String message, Throwable cause) {
        super(message, cause);
    }
}
