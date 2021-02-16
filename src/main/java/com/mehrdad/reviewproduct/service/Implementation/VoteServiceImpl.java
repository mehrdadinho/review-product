package com.mehrdad.reviewproduct.service.Implementation;

import com.mehrdad.reviewproduct.exception.BadRequestException;
import com.mehrdad.reviewproduct.exception.NotAcceptableException;
import com.mehrdad.reviewproduct.exception.RecordNotFoundException;
import com.mehrdad.reviewproduct.model.Product;
import com.mehrdad.reviewproduct.model.Vote;
import com.mehrdad.reviewproduct.model.enums.Commentable;
import com.mehrdad.reviewproduct.model.enums.Votable;
import com.mehrdad.reviewproduct.model.enums.VoteStatus;
import com.mehrdad.reviewproduct.repository.VoteRepository;
import com.mehrdad.reviewproduct.service.OrderDetailService;
import com.mehrdad.reviewproduct.service.ProductService;
import com.mehrdad.reviewproduct.service.UserService;
import com.mehrdad.reviewproduct.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by m.peykari on 2/15/2021.
 */
@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository repository;
    private final ProductService productService;
    private final UserService userService;

    private final OrderDetailService orderDetailService;

    @Autowired
    public VoteServiceImpl(VoteRepository repository, @Lazy ProductService productService,UserService userService, OrderDetailService orderDetailService) {
        this.repository = repository;
        this.productService = productService;
        this.userService = userService;
        this.orderDetailService = orderDetailService;
    }

    @Override
    public Vote save(Vote vote) throws RecordNotFoundException, BadRequestException {
        userService.getById(vote.getUser().getId());
        Product product = productService.getById(vote.getProduct().getId());
        if (product.getVotable()== Votable.UNVOTABLE){
            throw new BadRequestException("Vote is close for Product Id: "+vote.getProduct().getId());
        }else if (product.getCommentable()== Commentable.BUYERS) {
            Long purchaseCount = orderDetailService.getPurchaseCountByProductIdAndUserId(vote.getProduct().getId(),vote.getUser().getId());
            if (purchaseCount<1){
                throw new BadRequestException("Vote available just for users, who bought this product");
            }
        }
        vote.setId(null);
        vote.setVoteStatus(VoteStatus.WAITING);
        return repository.save(vote);
    }

    @Override
    public Vote changeStatus(Long id, Integer newStatus) throws RecordNotFoundException, NotAcceptableException {
        Vote vote = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Vote not found for Id: "+id));
        try {
            vote.setVoteStatus(VoteStatus.values()[newStatus]);
            return repository.save(vote);
        }catch (IndexOutOfBoundsException ex){
            throw new NotAcceptableException("Vote Status not found for value: "+newStatus);
        }
    }

    @Override
    public Float averageByProductId(Long ProductId) {
        return repository.averageByProductId(ProductId);
    }



    @Override
    public Vote getById(Long id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Vote not found for Id: "+id));
    }

    @Override
    public Vote update(Long id,Vote vote) throws RecordNotFoundException {
        Vote fetchEntity = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Vote not found for Id: "+id));
        fetchEntity.setVoteScore(vote.getVoteScore());
        vote.setVoteStatus(VoteStatus.WAITING);
        return repository.save(fetchEntity);
    }

    @Override
    public void deleteById(long id) throws RecordNotFoundException {
        final Vote vote = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Vote not found for Id: "+id));
        repository.delete(vote);
    }
}
