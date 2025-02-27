package com.mediawrangler.media_wrangler.controllers;


import com.mediawrangler.media_wrangler.data.CommentRepository;
import com.mediawrangler.media_wrangler.dto.CommentDTO;
import com.mediawrangler.media_wrangler.dto.MovieReviewDTO;
import com.mediawrangler.media_wrangler.dto.RatingDTO;
import com.mediawrangler.media_wrangler.models.Comment;
import com.mediawrangler.media_wrangler.models.MovieReview;
import com.mediawrangler.media_wrangler.services.CommentService;

import java.util.List;
import java.util.Optional;

import com.mediawrangler.media_wrangler.services.MovieReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/comments")
public class CommentController {


    @Autowired
    private final CommentService commentService;


    public CommentController(CommentService commentService, MovieReviewService movieReviewService) {
        this.commentService = commentService;
    }

    // for saving a comment...
    @PostMapping("/create")
    public ResponseEntity<?> createComment(@RequestBody CommentDTO commentDTO) {
        try {
            CommentDTO savedComment = commentService.addComment(commentDTO);
            return new ResponseEntity<>("Comment Submission successful", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while saving the comment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    // for rendering all comments associated with a movie review ...
    @GetMapping("/review/{movieReviewId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByMovieReviewId(@PathVariable Long movieReviewId) {
        List<CommentDTO> comments = commentService.findCommentsByMovieReviewId(movieReviewId);
        return ResponseEntity.ok(comments);
    }

    //For updating the comment...
    @PutMapping("/edit/{id}/{userId}")
    public ResponseEntity<?> updateComment(@PathVariable Long id, @PathVariable int userId, @Valid @RequestBody CommentDTO commentDTO) {
        System.out.println("Received request to update comment with ID: " + id + " by user: " + userId);
        System.out.println("Comment data: " + commentDTO);

        try {
            Optional<CommentDTO> updatedComment = commentService.updatedComment(id, commentDTO, userId);

            if (updatedComment.isPresent()) {
                System.out.println("Comment successfully updated: " + updatedComment.get());
                return new ResponseEntity<>(updatedComment.get(), HttpStatus.OK);
            } else {
                System.out.println("Comment not found or unauthorized");
                return new ResponseEntity<>("Comment not found or unauthorized", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while updating the comment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {

        System.out.println("Received request to delete comment with ID: " + id);


        try {
            Optional<CommentDTO> deletedComment = commentService.findCommentById(id);

            if (deletedComment.isPresent()) {
                System.out.println("Comment successfully deleted: " + deletedComment.get());

                commentService.deleteById(id);
                return new ResponseEntity<>(deletedComment.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Comment not found or unauthorized", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while deleting the comment", HttpStatus.NOT_FOUND);
        }
    }



}
