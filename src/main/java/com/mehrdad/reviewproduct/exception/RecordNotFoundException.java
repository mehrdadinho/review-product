package com.mehrdad.reviewproduct.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by m.peykari on 2/15/2021.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND)  // 404
public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String message) {
        super(message);
    }
}
