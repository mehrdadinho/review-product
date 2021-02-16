package com.mehrdad.reviewproduct.service.Implementation;

import com.mehrdad.reviewproduct.dto.CommentDto;
import com.mehrdad.reviewproduct.dto.ProductDto;
import com.mehrdad.reviewproduct.exception.BadRequestException;
import com.mehrdad.reviewproduct.exception.NotAcceptableException;
import com.mehrdad.reviewproduct.exception.RecordNotFoundException;
import com.mehrdad.reviewproduct.mapper.ProductMapper;
import com.mehrdad.reviewproduct.model.Product;
import com.mehrdad.reviewproduct.model.enums.Commentable;
import com.mehrdad.reviewproduct.model.enums.ProductStatus;
import com.mehrdad.reviewproduct.model.enums.Votable;
import com.mehrdad.reviewproduct.repository.ProductRepository;
import com.mehrdad.reviewproduct.service.CommentService;
import com.mehrdad.reviewproduct.service.ProductService;
import com.mehrdad.reviewproduct.service.ProviderService;
import com.mehrdad.reviewproduct.service.VoteService;
import com.mehrdad.reviewproduct.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Created by m.peykari on 2/15/2021.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final ProviderService providerService;
    private final CommentService commentService;
    private final VoteService voteService;
    private final Utility utility;

    @Autowired
    public ProductServiceImpl(ProductRepository repository, ProductMapper mapper, ProviderService providerService, CommentService commentService, VoteService voteService, Utility utility) {
        this.repository = repository;
        this.mapper = mapper;
        this.providerService = providerService;
        this.commentService = commentService;
        this.voteService = voteService;
        this.utility = utility;
    }

    @Override
    public Product save(Product product) throws RecordNotFoundException {
        providerService.getById(product.getProvider().getId());
        product.setId(null);
        return repository.save(product);
    }

    @Override
    public Product changeStatus(Long productId, Integer newStatus) throws RecordNotFoundException,NotAcceptableException {
        Product product = repository.findById(productId).orElseThrow(() -> new RecordNotFoundException("Product not found for Id: "+productId));
        try {
            product.setProductStatus(ProductStatus.values()[newStatus]);
            return repository.save(product);
        }catch (IndexOutOfBoundsException ex){
            throw new NotAcceptableException("Product Status not found for value: "+newStatus);
        }
    }

    @Override
    public Product changeCommentableType(Long productId, Integer commentableType) throws RecordNotFoundException,NotAcceptableException {
        Product product = repository.findById(productId).orElseThrow(() -> new RecordNotFoundException("Product not found for Id: "+productId));
        try {
            product.setCommentable(Commentable.values()[commentableType]);
            return repository.save(product);
        }catch (IndexOutOfBoundsException ex){
            throw new NotAcceptableException("Commentable Type not found for value: "+commentableType);
        }
    }

    @Override
    public Product changeVotableType(Long productId, Integer votableType) throws RecordNotFoundException,NotAcceptableException {
        Product product = repository.findById(productId).orElseThrow(() -> new RecordNotFoundException("Product not found for Id: "+productId));
        try {
            product.setVotable(Votable.values()[votableType]);
            return repository.save(product);
        }catch (IndexOutOfBoundsException ex){
            throw new NotAcceptableException("VotableType Type not found for value: "+votableType);
        }
    }

    @Override
    public Page<Product> search(String name, String fromDate, String toDate, BigDecimal fromCost, BigDecimal toCost, Long ProviderId,Integer page,Integer size) throws BadRequestException {
        return repository.search(name, utility.stringToDate(fromDate,false), utility.stringToDate(toDate,true), fromCost, toCost, ProviderId,PageRequest.of(page,size));
    }

    @Override
    public ProductDto getReviewById(Long productId) throws RecordNotFoundException {
        final Product product = getById(productId);
        final List<CommentDto> commentDtos = commentService.get3RecentByProductId(productId);
        Integer confirmedCommentCount = commentService.totalConfirmedByProductId(productId);
        final Float voteAverage = voteService.averageByProductId(productId);
        ProductDto productDto = mapper.toProductDto(product);
        productDto.setCommentDtoList(commentDtos);
        productDto.setTotalComments(confirmedCommentCount);
        productDto.setVoteAverage(voteAverage);
        return productDto;
    }

    @Override
    public Product getById(Long productId) throws RecordNotFoundException {
        return repository.findById(productId).orElseThrow(() -> new RecordNotFoundException("Product not found for Id: "+productId));
    }

    @Override
    public Product update(Long id,Product product) throws RecordNotFoundException {
        final Product fetchEntity = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Product not found for Id: "+id));
        fetchEntity.setVotable(product.getVotable());
        fetchEntity.setCommentable(product.getCommentable());
        fetchEntity.setProductStatus(product.getProductStatus());
        fetchEntity.setName(product.getName());
        fetchEntity.setProvider(product.getProvider());
        fetchEntity.setCost(product.getCost());
        fetchEntity.setCurrency(product.getCurrency());

        return repository.save(fetchEntity);
    }
}
