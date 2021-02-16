package com.mehrdad.reviewproduct.service;

import com.mehrdad.reviewproduct.dto.ProductDto;
import com.mehrdad.reviewproduct.exception.BadRequestException;
import com.mehrdad.reviewproduct.exception.NotAcceptableException;
import com.mehrdad.reviewproduct.exception.RecordNotFoundException;
import com.mehrdad.reviewproduct.model.Product;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

/**
 * Created by m.peykari on 2/15/2021.
 */
public interface ProductService {

    Product save(Product product) throws RecordNotFoundException;
    Product changeStatus(Long productId, Integer newStatus) throws RecordNotFoundException,NotAcceptableException;
    Product changeCommentableType(Long productId, Integer commentableType) throws RecordNotFoundException,NotAcceptableException;
    Product changeVotableType(Long productId, Integer votableType) throws RecordNotFoundException,NotAcceptableException;
    Page<Product> search(String name, String fromDate, String toDate, BigDecimal fromCost, BigDecimal toCost, Long ProviderId, Integer page, Integer size) throws BadRequestException;
    ProductDto getReviewById(Long productId) throws RecordNotFoundException;
    Product getById(Long productId) throws RecordNotFoundException;
    Product update(Long id, Product product) throws RecordNotFoundException;
}
