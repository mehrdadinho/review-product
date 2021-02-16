package com.mehrdad.reviewproduct.repository;

import com.mehrdad.reviewproduct.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by m.peykari on 2/15/2021.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p WHERE (:name is null or (p.name like :name)) " +
            "AND (:fromDate is null or (p.insertDate >= :fromDate))" +
            "AND (:toDate is null or (p.insertDate <= :toDate))" +
            "AND (:fromCost is null or (p.cost >= :fromCost))" +
            "AND (:toCost is null or (p.cost <= :toCost))" +
            "AND (:ProviderId is null or (p.provider.id = :ProviderId)) ")
    Page<Product> search(@Param("name") String name, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate,
                         @Param("fromCost") BigDecimal fromCost, @Param("toCost") BigDecimal toCost,
                         @Param("ProviderId") Long ProviderId, Pageable pageable);

}