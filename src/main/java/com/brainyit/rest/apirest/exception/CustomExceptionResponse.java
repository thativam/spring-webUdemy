package com.brainyit.rest.apirest.exception;

import java.util.Date;

public record CustomExceptionResponse(Date timestamp, String message, String errorDescription) {
}
