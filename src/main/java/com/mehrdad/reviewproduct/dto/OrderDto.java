package com.mehrdad.reviewproduct.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mehrdad.reviewproduct.model.OrderDetail;
import com.mehrdad.reviewproduct.model.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Set;

/**
 * Created by m.peykari on 2/15/2021.
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {

    private Long id;
    private Integer orderStatusIndex;
    private String orderStatusTitle;
    private User user;
    private Set<OrderDetail> orderDetails;
    private Date date;

}


