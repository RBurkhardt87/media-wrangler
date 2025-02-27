package com.mediawrangler.media_wrangler.data;

import com.mediawrangler.media_wrangler.dto.ReplyDTO;
import com.mediawrangler.media_wrangler.models.Comment;
import com.mediawrangler.media_wrangler.models.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    //find all comments for a single review -- to render with the user review card
    List<Reply> findByCommentId(Long commentId);

    Optional<Reply> findByIdAndUserId(Long id, int userId);

}
