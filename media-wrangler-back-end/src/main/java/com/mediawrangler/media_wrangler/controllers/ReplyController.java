package com.mediawrangler.media_wrangler.controllers;


import com.mediawrangler.media_wrangler.dto.CommentDTO;
import com.mediawrangler.media_wrangler.dto.ReplyDTO;
import com.mediawrangler.media_wrangler.services.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/replies")
public class ReplyController {


    @Autowired
    private ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }



    //* creating/submitting a user reply but with more info for debugging. Returning the actual reply being saved. Using
    //* RuntimeException is more specific handling and actually retrieving the error message.
    @PostMapping("/create")
    public ResponseEntity<?> createUserReply(@RequestBody ReplyDTO replyDTO) {
        try {
            ReplyDTO savedReply = replyService.addUserReply(replyDTO);
            return new ResponseEntity<>(savedReply, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/view/{commentId}")
    public ResponseEntity<List<ReplyDTO>> getCommentsByMovieReviewId(@PathVariable Long commentId) {
        List<ReplyDTO> replies = replyService.findRepliesByCommentId(commentId);

        // this will return an array, whether it is empty or not
        return ResponseEntity.ok(replies);
    }


}
