package com.mehrdad.reviewproduct.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mehrdad.reviewproduct.model.enums.Currency;
import com.mehrdad.reviewproduct.model.enums.ProductStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by m.peykari on 2/15/2021.
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

    private Long id;
    private String name;
    private Date insertDate;
    private BigDecimal cost;
    private Currency currency;
    private Integer productStatusIndex;
    private String productStatusTitle;
    private Integer commentableIndex;
    private String commentableTitle;
    private Integer votableIndex;
    private String votableTitle;
    private Long providerId;
    private Float voteAverage;
    private Integer totalComments;
    private List<CommentDto> commentDtoList;
}


