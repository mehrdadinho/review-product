package com.mehrdad.reviewproduct.mapper;


import com.mehrdad.reviewproduct.dto.ProductDto;
import com.mehrdad.reviewproduct.exception.NotAcceptableException;
import com.mehrdad.reviewproduct.model.Product;
import com.mehrdad.reviewproduct.model.enums.Commentable;
import com.mehrdad.reviewproduct.model.enums.ProductStatus;
import com.mehrdad.reviewproduct.model.enums.Votable;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by m.peykari on 2/15/2021.
 */
@Component
public class ProductMapper extends ModelMapper {

    public ProductDto toProductDto(Product product) {
        ProductDto productDto = map(product, ProductDto.class);
        return productDto;
    }

    public Product toProduct(ProductDto productDto) {
        Product product = map(productDto, Product.class);

        try {
            if (productDto.getProductStatusIndex() != null) {
                product.setProductStatus(ProductStatus.values()[productDto.getProductStatusIndex()]);
            }
        }catch (IndexOutOfBoundsException ex){
            throw new NotAcceptableException("Product Status not found for value: "+productDto.getProductStatusIndex());
        }

        try {
            if (productDto.getCommentableIndex() != null) {
                product.setCommentable(Commentable.values()[productDto.getCommentableIndex()]);
            }
        }catch (IndexOutOfBoundsException ex){
            throw new NotAcceptableException("Commentable type not found for value: "+productDto.getCommentableIndex());
        }

        try {
            if (productDto.getVotableIndex() != null) {
                product.setVotable(Votable.values()[productDto.getVotableIndex()]);
            }
        }catch (IndexOutOfBoundsException ex){
            throw new NotAcceptableException("Votable type not found for value: "+productDto.getVotableIndex());
        }
        return product;
    }

    public List<ProductDto> toProductDtoList(List<Product> products){
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add(toProductDto(product));
        }
        return productDtos;
    }
}
