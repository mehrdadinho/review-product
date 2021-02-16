package com.mehrdad.reviewproduct.repository;

import com.mehrdad.reviewproduct.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by m.peykari on 2/14/2021.
 */
@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {

    @Query("SELECT AVG(v.voteScore) FROM Vote v WHERE v.product.id = :productId and v.voteStatus = 1")
    Float averageByProductId(@Param("productId") Long productId);

}