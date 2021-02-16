package com.mehrdad.reviewproduct.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by m.peykari on 2/16/2021.
 */
@ResponseStatus(value= HttpStatus.NOT_ACCEPTABLE)  // 406
public class NotAcceptableException extends RuntimeException {

    public NotAcceptableException(String message) { super(message); }

}
