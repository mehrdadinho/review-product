package com.mehrdad.reviewproduct.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by m.peykari on 2/16/2021.
 */
@ResponseStatus(value= HttpStatus.BAD_REQUEST)  // 400
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) { super(message); }

}
