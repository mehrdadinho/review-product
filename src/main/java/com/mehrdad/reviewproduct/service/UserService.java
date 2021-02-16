package com.mehrdad.reviewproduct.service;

import com.mehrdad.reviewproduct.exception.RecordNotFoundException;
import com.mehrdad.reviewproduct.model.User;

/**
 * Created by m.peykari on 2/15/2021.
 */
public interface UserService {
    User getById(Long id) throws RecordNotFoundException;

}
