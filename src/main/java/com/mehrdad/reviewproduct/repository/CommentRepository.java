package com.mehrdad.reviewproduct.repository;

import com.mehrdad.reviewproduct.model.Comment;
import com.mehrdad.reviewproduct.model.enums.CommentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by m.peykari on 2/14/2021.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findFirst3ByProductIdOrderByIdDesc(Long productId);
    Integer countByProductIdAndCommentStatus(Long productId, CommentStatus commentStatus);

}