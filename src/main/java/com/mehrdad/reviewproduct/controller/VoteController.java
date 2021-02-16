package com.mehrdad.reviewproduct.controller;

import com.mehrdad.reviewproduct.dto.VoteDto;
import com.mehrdad.reviewproduct.mapper.VoteMapper;
import com.mehrdad.reviewproduct.model.Vote;
import com.mehrdad.reviewproduct.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by m.peykari on 2/15/2021.
 */
@RestController
@RequestMapping("/api/vote")
public class VoteController {

    private final VoteMapper mapper;
    private final VoteService service;

    @Autowired
    public VoteController(VoteMapper mapper, VoteService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<VoteDto> addVote(@RequestBody VoteDto voteDto){
        Vote vote = mapper.toVote(voteDto);
        final Vote savedVote = service.save(vote);
        final VoteDto retrieve = mapper.toVoteDto(savedVote);
        return ResponseEntity.ok(retrieve);
    }

    @PutMapping("/changeStatus/{voteId}/{statusType}")
    public ResponseEntity<VoteDto> changeStatus(@PathVariable Long voteId, @PathVariable Integer statusType){
        final Vote ChangedVote = service.changeStatus(voteId, statusType);
        final VoteDto retrieve = mapper.toVoteDto(ChangedVote);
        return ResponseEntity.ok(retrieve);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<VoteDto> getById(@PathVariable Long id) {
        final Vote vote = service.getById(id);
        final VoteDto voteDto = mapper.toVoteDto(vote);
        return ResponseEntity.ok(voteDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VoteDto> update(@PathVariable Long id,@RequestBody VoteDto voteDto){
        Vote vote = mapper.toVote(voteDto);
        final Vote savedVote = service.update(id,vote);
        final VoteDto retrieve = mapper.toVoteDto(savedVote);
        return ResponseEntity.ok(retrieve);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
