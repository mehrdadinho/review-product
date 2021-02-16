package com.mehrdad.reviewproduct.mapper;


import com.mehrdad.reviewproduct.dto.CommentDto;
import com.mehrdad.reviewproduct.exception.NotAcceptableException;
import com.mehrdad.reviewproduct.model.Comment;
import com.mehrdad.reviewproduct.model.enums.CommentStatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by m.peykari on 2/15/2021.
 */
@Component
public class CommentMapper extends ModelMapper {

    public CommentDto toCommentDto(Comment comment) {
        CommentDto commentDto = map(comment, CommentDto.class);
        return commentDto;
    }

    public Comment toComment(CommentDto commentDto) {
        Comment comment = map(commentDto, Comment.class);

        try {
            if (commentDto.getCommentStatusIndex() != null) {
                comment.setCommentStatus(CommentStatus.values()[commentDto.getCommentStatusIndex()]);
            }
        }catch (IndexOutOfBoundsException ex){
            throw new NotAcceptableException("Comment Status not found for value: "+commentDto.getCommentStatusIndex());
        }
        return comment;
    }

    public List<CommentDto> toCommentDtoList(List<Comment> comments){
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : comments) {
            commentDtos.add(toCommentDto(comment));
        }
        return commentDtos;
    }
}
