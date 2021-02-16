package com.mehrdad.reviewproduct.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mehrdad.reviewproduct.model.Product;
import com.mehrdad.reviewproduct.model.User;
import com.mehrdad.reviewproduct.model.enums.CommentStatus;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * Created by m.peykari on 2/15/2021.
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDto {

    private Long id;
    private String title;
    private String text;
    private Long userId;
    private Long productId;
    private Integer commentStatusIndex;
    private String commentStatusTitle;

}