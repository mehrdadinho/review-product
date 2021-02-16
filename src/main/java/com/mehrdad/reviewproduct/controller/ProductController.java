package com.mehrdad.reviewproduct.controller;

import com.mehrdad.reviewproduct.dto.ProductDto;
import com.mehrdad.reviewproduct.mapper.ProductMapper;
import com.mehrdad.reviewproduct.model.Product;
import com.mehrdad.reviewproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Created by m.peykari on 2/15/2021.
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductMapper mapper;
    private final ProductService service;

    @Autowired
    public ProductController(ProductMapper mapper, ProductService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping("/grid")
    public ResponseEntity<Page<ProductDto>> search(String name, String fromDate, String toDate,
                                                   BigDecimal fromCost,BigDecimal toCost,Long providerId,
                                                   @RequestParam Integer page,@RequestParam Integer size){
        Page<Product> productPage = productPage = service.search(name,fromDate,toDate,fromCost,toCost,providerId,page,size);
        Page<ProductDto> productDtoPage = productPage.map(product -> mapper.toProductDto(product));
        return ResponseEntity.ok(productDtoPage);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Long id) {
            final ProductDto productDto = service.getReviewById(id);
            return ResponseEntity.ok(productDto);
    }


    @PostMapping("/save")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto){
        Product product = mapper.toProduct(productDto);
        final Product savedProduct = service.save(product);
        final ProductDto retrieve = mapper.toProductDto(savedProduct);
        return ResponseEntity.ok(retrieve);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody ProductDto productDto){
        Product product = mapper.toProduct(productDto);
        final Product savedProduct = service.update(id,product);
        final ProductDto retrieve = mapper.toProductDto(savedProduct);
        return ResponseEntity.ok(retrieve);
    }

    @PutMapping("/changeStatus/{productId}/{statusType}")
    public ResponseEntity<ProductDto> changeStatus(@PathVariable Long productId, @PathVariable Integer statusType){
        final Product ChangedProduct = service.changeStatus(productId, statusType);
        final ProductDto retrieve = mapper.toProductDto(ChangedProduct);
        return ResponseEntity.ok(retrieve);
    }

    @PutMapping("/commentable/{productId}/{commentableType}")
    public ResponseEntity<ProductDto> changeCommentableType(@PathVariable Long productId, @PathVariable Integer commentableType){
        final Product ChangedProduct = service.changeCommentableType(productId, commentableType);
        final ProductDto retrieve = mapper.toProductDto(ChangedProduct);
        return ResponseEntity.ok(retrieve);
    }

    @PutMapping("/votable/{productId}/{votableType}")
    public ResponseEntity<ProductDto> changeVotableType(@PathVariable Long productId, @PathVariable Integer votableType){
        final Product ChangedProduct = service.changeVotableType(productId, votableType);
        final ProductDto retrieve = mapper.toProductDto(ChangedProduct);
        return ResponseEntity.ok(retrieve);
    }

}
