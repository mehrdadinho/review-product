package com.mehrdad.reviewproduct.controller;

import com.mehrdad.reviewproduct.dto.CommentDto;
import com.mehrdad.reviewproduct.mapper.CommentMapper;
import com.mehrdad.reviewproduct.model.Comment;
import com.mehrdad.reviewproduct.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by m.peykari on 2/15/2021.
 */
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentMapper mapper;
    private final CommentService service;

    @Autowired
    public CommentController(CommentMapper mapper, CommentService service) {
        this.mapper = mapper;
        this.service = service;
    }

    /**
     * This method get a new comment object
     * and save it to the database
     *
     * @param commentDto commentDto object
     *               entity includes:
     *                <ul>
     *                <li> title </li>
     *                <li> text  </li>
     *                <li> userId </li>
     *                <li> productId  </li>
     *                <li> commentStatusIndex (nullable)  </li>
     *                </ul>
     *
     * @return the ResponseEntity.Ok with comment object inside the body.
     */
    @PostMapping("/save")
    public ResponseEntity<CommentDto> save(@RequestBody CommentDto commentDto){
        Comment comment = mapper.toComment(commentDto);
        final Comment savedComment = service.save(comment);
        final CommentDto retrieve = mapper.toCommentDto(savedComment);
        return ResponseEntity.ok(retrieve);
    }

    /**
     * This method change the status of comment entity
     *
     * @param commentId id of comment entity
     * @param statusType new status type of comment
     *
     * @return the ResponseEntity.Ok with updated comment object inside the body.
     */
    @PutMapping("/changeStatus/{commentId}/{statusType}")
    public ResponseEntity<CommentDto> changeStatus(@PathVariable Long commentId, @PathVariable Integer statusType){
        final Comment ChangedComment = service.changeStatus(commentId, statusType);
        final CommentDto retrieve = mapper.toCommentDto(ChangedComment);
        return ResponseEntity.ok(retrieve);
    }

    /**
     * This method get a comment id
     * and send back the full comment object
     *
     * @param id id of comment
     *
     * @return the ResponseEntity.Ok with fetched comment object inside the body.
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<CommentDto> getById(@PathVariable Long id) {
        final Comment comment = service.getById(id);
        final CommentDto commentDto = mapper.toCommentDto(comment);
        return ResponseEntity.ok(commentDto);
    }

    /**
     * This method update a comment object
     *
     * @param commentDto commentDto object
     *               entity includes:
     *                <ul>
     *                <li> title </li>
     *                <li> text  </li>
     *                </ul>
     * @param id id of comment object in database
     *
     * @return the ResponseEntity.Ok with updated comment object inside the body.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,@RequestBody CommentDto commentDto){
        Comment comment = mapper.toComment(commentDto);
        final Comment savedComment = service.update(id,comment);
        final CommentDto retrieve = mapper.toCommentDto(savedComment);
        return ResponseEntity.ok(retrieve);
    }

    /**
     * This method get a specific comment id
     * and delete it from database.
     *
     * @param id id of to be deleted object
     *
     * @return the ResponseEntity.Ok
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
