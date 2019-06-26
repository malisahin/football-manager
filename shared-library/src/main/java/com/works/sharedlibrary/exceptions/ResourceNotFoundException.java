package com.works.sharedlibrary.exceptions;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */

public abstract class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
