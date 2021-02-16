package com.mehrdad.reviewproduct.service.Implementation;

import com.mehrdad.reviewproduct.repository.OrderRepository;
import com.mehrdad.reviewproduct.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by m.peykari on 2/15/2021.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Autowired
    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }
}
