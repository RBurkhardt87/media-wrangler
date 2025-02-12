package com.mediawrangler.media_wrangler.dto;

import java.time.LocalDate;

public class ReplyDTO {

    private Long id;
    private String userReply;
    private Long commentId;
    private int userId;
    private String username;
    private LocalDate dateCreated;



    public ReplyDTO() {
    }

    public ReplyDTO(Long id, String userReply, Long commentId, int userId, String username, LocalDate dateCreated) {
        this.id = id;
        this.userReply = userReply;
        this.commentId = commentId;
        this.userId = userId;
        this.username = username;
        this.dateCreated = dateCreated;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserReply() {
        return userReply;
    }

    public void setUserReply(String userReply) {
        this.userReply = userReply;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }
}
