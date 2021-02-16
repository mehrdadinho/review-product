package com.mehrdad.reviewproduct.service.Implementation;

import com.mehrdad.reviewproduct.repository.OrderDetailRepository;
import com.mehrdad.reviewproduct.repository.OrderRepository;
import com.mehrdad.reviewproduct.service.OrderDetailService;
import com.mehrdad.reviewproduct.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by m.peykari on 2/15/2021.
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository repository;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long getPurchaseCountByProductIdAndUserId(Long productId, Long userId) {
        return repository.getPurchaseCountByProductIdAndUserId(productId,userId);
    }
}
