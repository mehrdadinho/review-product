package com.mehrdad.reviewproduct.service;

/**
 * Created by m.peykari on 2/15/2021.
 */
public interface OrderDetailService {
    Long getPurchaseCountByProductIdAndUserId( Long productId, Long userId);
}
