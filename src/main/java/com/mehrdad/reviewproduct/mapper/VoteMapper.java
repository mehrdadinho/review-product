package com.mehrdad.reviewproduct.mapper;


import com.mehrdad.reviewproduct.dto.VoteDto;
import com.mehrdad.reviewproduct.dto.VoteDto;
import com.mehrdad.reviewproduct.exception.NotAcceptableException;
import com.mehrdad.reviewproduct.model.Vote;
import com.mehrdad.reviewproduct.model.enums.VoteScore;
import com.mehrdad.reviewproduct.model.enums.VoteStatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Created by m.peykari on 2/15/2021.
 */
@Component
public class VoteMapper extends ModelMapper {

    public VoteDto toVoteDto(Vote vote) {
        VoteDto voteDto = map(vote, VoteDto.class);
        return voteDto;
    }

    public Vote toVote(VoteDto voteDto) {
        Vote vote = map(voteDto, Vote.class);

        try{
            if (voteDto.getVoteStatusIndex() != null ){
                vote.setVoteStatus(VoteStatus.values()[voteDto.getVoteStatusIndex()]);
            }
        }catch (IndexOutOfBoundsException ex){
            throw new NotAcceptableException("Vote Status not found for value: "+voteDto.getVoteStatusIndex());
        }

        try{
            if (voteDto.getVoteScoreIndex() != null ) {
                vote.setVoteScore(VoteScore.values()[voteDto.getVoteScoreIndex()]);
            }
        }catch (IndexOutOfBoundsException ex){
            throw new NotAcceptableException("Vote Score not found for value: "+voteDto.getVoteScoreIndex());
        }

        return vote;
    }
}
