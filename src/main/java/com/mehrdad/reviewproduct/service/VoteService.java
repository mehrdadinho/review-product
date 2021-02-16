package com.mehrdad.reviewproduct.service;

import com.mehrdad.reviewproduct.exception.BadRequestException;
import com.mehrdad.reviewproduct.exception.NotAcceptableException;
import com.mehrdad.reviewproduct.exception.RecordNotFoundException;
import com.mehrdad.reviewproduct.model.Vote;

/**
 * Created by m.peykari on 2/15/2021.
 */
public interface VoteService {

    Vote save(Vote vote) throws RecordNotFoundException, BadRequestException;
    Vote changeStatus(Long id,Integer newStatus) throws RecordNotFoundException, NotAcceptableException;
    Float averageByProductId(Long ProductId);
    Vote getById(Long id) throws RecordNotFoundException;
    Vote update(Long id, Vote vote) throws RecordNotFoundException;
    void deleteById(long id) throws RecordNotFoundException;

}
