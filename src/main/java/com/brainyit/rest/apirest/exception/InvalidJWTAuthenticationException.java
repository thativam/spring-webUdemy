package com.brainyit.rest.apirest.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidJWTAuthenticationException extends AuthenticationException {
    public InvalidJWTAuthenticationException(final String msg) {
        super(msg);
    }
}
