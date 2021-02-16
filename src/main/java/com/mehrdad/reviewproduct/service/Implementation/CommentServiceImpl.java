package com.mehrdad.reviewproduct.service.Implementation;

import com.mehrdad.reviewproduct.dto.CommentDto;
import com.mehrdad.reviewproduct.exception.BadRequestException;
import com.mehrdad.reviewproduct.exception.NotAcceptableException;
import com.mehrdad.reviewproduct.exception.RecordNotFoundException;
import com.mehrdad.reviewproduct.mapper.CommentMapper;
import com.mehrdad.reviewproduct.model.Comment;
import com.mehrdad.reviewproduct.model.Product;
import com.mehrdad.reviewproduct.model.enums.CommentStatus;
import com.mehrdad.reviewproduct.model.enums.Commentable;
import com.mehrdad.reviewproduct.repository.CommentRepository;
import com.mehrdad.reviewproduct.service.CommentService;
import com.mehrdad.reviewproduct.service.OrderDetailService;
import com.mehrdad.reviewproduct.service.ProductService;
import com.mehrdad.reviewproduct.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by m.peykari on 2/15/2021.
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final CommentMapper mapper;
    private final ProductService productService;
    private final UserService userService;
    private final OrderDetailService orderDetailService;

    @Autowired
    public CommentServiceImpl(CommentRepository repository, CommentMapper mapper, @Lazy ProductService productService,UserService userService, OrderDetailService orderDetailService) {
        this.repository = repository;
        this.mapper = mapper;
        this.productService = productService;
        this.userService = userService;
        this.orderDetailService = orderDetailService;
    }

    @Override
    public Comment save(Comment comment) throws RecordNotFoundException, BadRequestException {
        userService.getById(comment.getUser().getId());
        Product product = productService.getById(comment.getProduct().getId());
        if (product.getCommentable()== Commentable.UNCOMMENTABLE){
            throw new BadRequestException("Comment is close for Product Id: "+comment.getProduct().getId());
        }else if (product.getCommentable()== Commentable.BUYERS) {
            Long purchaseCount = orderDetailService.getPurchaseCountByProductIdAndUserId(comment.getProduct().getId(),comment.getUser().getId());
            if (purchaseCount<1){
                throw new BadRequestException("Comment available just for users, who bought this product");
            }
        }
        comment.setId(null);
        comment.setCommentStatus(CommentStatus.WAITING);
        return repository.save(comment);
    }

    @Override
    public Comment changeStatus(Long commentId, Integer newStatus) throws RecordNotFoundException, NotAcceptableException {
        final Comment comment = repository.findById(commentId).orElseThrow(() -> new RecordNotFoundException("Comment not found for Id: "+commentId));
        try {
            comment.setCommentStatus(CommentStatus.values()[newStatus]);
            return repository.save(comment);
        }catch (IndexOutOfBoundsException ex){
            throw new NotAcceptableException("Comment Status not found for value: "+newStatus);
        }
    }

    @Override
    public List<CommentDto> get3RecentByProductId(Long productId) {
        return mapper.toCommentDtoList(repository.findFirst3ByProductIdOrderByIdDesc(productId));
    }

    @Override
    public Integer totalConfirmedByProductId(Long productId) {
        return repository.countByProductIdAndCommentStatus(productId,CommentStatus.CONFIRMED);
    }

    @Override
    public Comment getById(Long id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Comment not found for Id: "+id));
    }

    @Override
    public Comment update(Long id,Comment comment) throws RecordNotFoundException {
        Comment fetchEntity = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Comment not found for Id: "+id));
        fetchEntity.setTitle(comment.getTitle());
        fetchEntity.setText(comment.getText());
        comment.setCommentStatus(CommentStatus.WAITING);
        return repository.save(fetchEntity);
    }

    @Override
    public void deleteById(long id) throws RecordNotFoundException {
        final Comment comment = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Comment not found for Id: "+id));
        repository.delete(comment);
    }
}
