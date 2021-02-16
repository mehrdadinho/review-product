package com.mehrdad.reviewproduct.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mehrdad.reviewproduct.model.Product;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * Created by m.peykari on 2/15/2021.
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProviderDto {

    private Long id;
    private String name;
    private String supportPhone;
    private Set<ProductDto> products;
}


