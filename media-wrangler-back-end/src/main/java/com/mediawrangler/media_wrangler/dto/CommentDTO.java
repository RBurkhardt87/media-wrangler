package com.mediawrangler.media_wrangler.dto;

import com.mediawrangler.media_wrangler.models.Comment;

public class CommentDTO {


    private Long id;
    private String userComment;
    private Long movieReviewId;
    private int userId;
    private String username;
    private String firstname;
    private String lastname;


    
    public CommentDTO() {
    }

    public CommentDTO(Long id, String userComment, Long movieReviewId, int userId, String username, String firstname, String lastname) {
        this.id = id;
        this.userComment = userComment;
        this.movieReviewId = movieReviewId;
        this.userId = userId;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    //This is to be able to store a deleted comment in a DTO to return which comment has been deleted...
    public CommentDTO(Comment deleteComment) {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public Long getMovieReviewId() {
        return movieReviewId;
    }

    public void setMovieReviewId(Long movieReviewId) {
        this.movieReviewId = movieReviewId;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
