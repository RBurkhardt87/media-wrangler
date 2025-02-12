package com.mediawrangler.media_wrangler.services;

import com.mediawrangler.media_wrangler.data.CommentRepository;
import com.mediawrangler.media_wrangler.data.ReplyRepository;
import com.mediawrangler.media_wrangler.data.UserRepository;
import com.mediawrangler.media_wrangler.dto.CommentDTO;
import com.mediawrangler.media_wrangler.dto.ReplyDTO;
import com.mediawrangler.media_wrangler.models.Comment;
import com.mediawrangler.media_wrangler.models.MovieReview;
import com.mediawrangler.media_wrangler.models.Reply;
import com.mediawrangler.media_wrangler.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    public ReplyService(ReplyRepository replyRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.replyRepository = replyRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }


//    public ReplyDTO addReply(ReplyDTO replyDTO) {
//
//        User user = userRepository.findById((replyDTO.getUserId()))
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Comment comment = commentRepository.findById(replyDTO.getCommentId())
//                .orElseThrow(() -> new RuntimeException("Comment not found"));
//
//        Reply reply = new Reply();
//        reply.setUserReply(replyDTO.getUserReply());
//        reply.setUser(user);
//        reply.setComment(comment);
//
//        Reply savedReply = replyRepository.save(reply);
//
//        ReplyDTO savedReplyDTO = new ReplyDTO();
//        savedReplyDTO.setId(savedReply.getId());
//        savedReplyDTO.setCommentId(savedReply.getComment().getId());
//        savedReplyDTO.setUserId(savedReply.getUser().getId());
//        savedReplyDTO.setUserReply(savedReply.getUserReply());
//
//        return savedReplyDTO;
//    }

    //* Try out a new way to set ReplyDTO to a Reply Entity and return by as a ReplyDTO...
    public ReplyDTO addUserReply(ReplyDTO replyDTO) {
        // Must check database for the user
        User user = userRepository.findById(replyDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Must check database for the comment
        Comment comment = commentRepository.findById(replyDTO.getCommentId())
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        // Create and set up the Reply entity-- based off the Reply Constructor (userReply, user, comment)
        Reply reply = new Reply(replyDTO.getUserReply(), user, comment);

        // Save the reply to the database --using repository to save the reply
        Reply savedReply = replyRepository.save(reply);

        // Once reply is saved -- Return a DTO with the necessary details
        return new ReplyDTO(savedReply.getId(), savedReply.getUserReply(),
                savedReply.getComment().getId(), savedReply.getUser().getId(), savedReply.getUser().getUsername(),
                savedReply.getDateCreated());
    }

    //* retrieve all the replies to a comment...
//    public List<ReplyDTO> findRepliesByCommentId(Long commentId) {
//
//        List<Reply> replies = replyRepository.findByCommentId(commentId);
//        List<ReplyDTO> replyDTOS = new ArrayList<>();
//
//        for (Reply reply : replies) {
//            ReplyDTO dto = new ReplyDTO();
//
//            dto.setId(reply.getId());
//            dto.setUserId(reply.getUser().getId());
//            dto.setUserReply(reply.getUserReply());
//            dto.setCommentId(reply.getComment().getId());
//            dto.setUsername(reply.getUser().getUsername());
//
//            replyDTOS.add(dto);
//        }
//        return replyDTOS;
//    }



    //* update the retrieve all replies to a comment. Incorporate stream()...
    public List<ReplyDTO> findRepliesByCommentId(Long commentId) {
        return replyRepository.findByCommentId(commentId).stream()
                .map(reply -> new ReplyDTO(
                        reply.getId(),
                        reply.getUserReply(),
                        reply.getComment().getId(),
                        reply.getUser().getId(),
                        reply.getUser().getUsername(),
                        reply.getDateCreated()  // If needed
                ))
                .collect(Collectors.toList());
    }

}
