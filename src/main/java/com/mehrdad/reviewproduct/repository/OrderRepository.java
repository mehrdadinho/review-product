package com.mehrdad.reviewproduct.repository;

import com.mehrdad.reviewproduct.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by m.peykari on 2/14/2021.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

}