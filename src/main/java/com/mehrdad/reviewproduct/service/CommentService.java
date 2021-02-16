package com.mehrdad.reviewproduct.service;

import com.mehrdad.reviewproduct.dto.CommentDto;
import com.mehrdad.reviewproduct.exception.BadRequestException;
import com.mehrdad.reviewproduct.exception.NotAcceptableException;
import com.mehrdad.reviewproduct.exception.RecordNotFoundException;
import com.mehrdad.reviewproduct.model.Comment;

import java.util.List;

/**
 * Created by m.peykari on 2/15/2021.
 */
public interface CommentService {

    Comment save(Comment comment) throws RecordNotFoundException, BadRequestException;
    Comment changeStatus(Long commentId,Integer newStatus) throws RecordNotFoundException, NotAcceptableException;
    List<CommentDto> get3RecentByProductId(Long productId);
    Integer totalConfirmedByProductId(Long productId);
    Comment getById(Long id) throws RecordNotFoundException;
    Comment update(Long id, Comment comment) throws RecordNotFoundException;
    void deleteById(long id) throws RecordNotFoundException;

}
