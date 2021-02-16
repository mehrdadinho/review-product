package com.mehrdad.reviewproduct.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mehrdad.reviewproduct.model.Product;
import com.mehrdad.reviewproduct.model.User;
import com.mehrdad.reviewproduct.model.enums.VoteScore;
import com.mehrdad.reviewproduct.model.enums.VoteStatus;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by m.peykari on 2/15/2021.
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoteDto {

    private Long id;
    private Integer voteScoreIndex;
    private Long userId;
    private Long productId;
    private Integer voteStatusIndex;
    private String voteStatusTitle;

}


