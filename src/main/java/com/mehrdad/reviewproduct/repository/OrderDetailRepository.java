package com.mehrdad.reviewproduct.repository;

import com.mehrdad.reviewproduct.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by m.peykari on 2/14/2021.
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {

    @Query(value = "SELECT COUNT(*) FROM order_detail od WHERE od.product_id = :productId AND od.order_id in (select o.id from Orders o where o.user_id = :userId and o.status = 3)",
            nativeQuery = true)
    Long getPurchaseCountByProductIdAndUserId(@Param("productId") Long productId, @Param("userId") Long userId);
}