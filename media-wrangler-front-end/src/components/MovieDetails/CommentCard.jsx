import React, { useState, useEffect } from 'react';
import { Typography, Button, Card, CardActions, TextField } from '@mui/material';
import { useAuth } from '../../Services/AuthContext';
import AvatarHeader from '../Profile/AvatarHeader';
import '../../stylings/MovieDetailsPage.css';
import { updateComment, deleteComment } from '../../Services/CommentService';



const CommentCard = ({ comment, onUpdate, showButtonTrigger }) => {
    const [showUserButtons, setShowUserButtons] = useState(false);
    const [showReplyButton, setReplyButton] = useState(false);
    const [isEditing, setEditing] = useState(false);
    const [editedText, setEditedText] = useState(comment.userComment); 

    const { user } = useAuth();

    useEffect(() => {
        if (user.id === comment.userId) {
            setShowUserButtons(true);
        } else {
            setReplyButton(true);
        }
    }, [user, comment.userId, comment.userComment, showButtonTrigger]);

    function handleEditClick() {
        setEditing(true);
    }



    //NOTE: By adding in the onUpdate, it will toggle the refreshTrigger state in MovieReviewListCard-- which then will retrigger useEffect that's fetching the comments... 
    async function handleSaveClick() {
        const updatedData = { ...comment, userComment: editedText };
        const response = await updateComment(updatedData);

        if (response === "Success") {
            setEditing(false);
            onUpdate();
        } else {
            console.error("Failed to update comment");
        }
    }

    function handleCancelClick() {
        setEditedText(comment.userComment);
        setEditing(false);
    }

    async function handleDeleteClick() {
        const response = await deleteComment(comment);

        if (response === "Success") {
            onUpdate();
            console.log("Comment deletion successful");
        } else {
            console.error("Failed to delete comment");
        }

    }

    function handleReplyClick() {
        console.log("user clicked reply");
    }

    return (
        <div>
            <Card sx={{ background: "rgba(19, 19, 20, 0.71)" }}>           
                <div className="avatar-username-rating-container">     
                    <AvatarHeader
                        firstname={comment.firstname}
                        lastname={comment.lastname}
                        styling="comment-avatar"
                    />
                    <div className="username-profile-link-comment">{comment.username}</div>
                </div>
                
                <div className="comments-section">
                    {isEditing ? (
                        <TextField
                            label= "Edit Comment"
                            fullWidth
                            multiline
                            value={editedText}
                            onChange={(e) => setEditedText(e.target.value)}
                            sx={{ marginBottom: 2,
                                "& .MuiInputBase-root": {
                                  color: "white", 
                                },
                                "& .MuiInputLabel-root": {
                                  color: "white", 
                                },
                                "& .MuiOutlinedInput-notchedOutline": {
                                  borderColor: "white", 
                                },
                                "& .MuiOutlinedInput-root:hover .MuiOutlinedInput-notchedOutline": {
                                  borderColor: "#ff8f00", 
                                }, }}
                        />
                    ) : (
                        <Typography variant="body2">
                            {comment.userComment}
                        </Typography>
                    )}
                </div>


                <CardActions>
                    {showReplyButton && <Button size="small" onClick={handleReplyClick}>Reply</Button>}

                    {showUserButtons && (
                        <div>
                            {isEditing ? (
                                <>
                                    <Button size="small" color="primary" onClick={handleSaveClick}>Save</Button>
                                    <Button size="small" color="secondary" onClick={handleCancelClick}>Cancel</Button>
                                </>
                            ) : (
                                <>
                                    <Button size="small" onClick={handleEditClick}>Edit</Button>
                                    <Button size="small" color="error" onClick={handleDeleteClick}>Delete</Button>
                                </>
                            )}
                        </div>
                    )}
                </CardActions>
            </Card>
        </div>    
    );
};

export default CommentCard;
