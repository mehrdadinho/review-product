package com.mehrdad.reviewproduct.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mehrdad.reviewproduct.model.Order;
import com.mehrdad.reviewproduct.model.Product;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by m.peykari on 2/15/2021.
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailDto {

    private Long id;
    private Order order;
    private Product product;
    private Integer count;

}


