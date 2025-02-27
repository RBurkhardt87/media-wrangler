import axios from "axios";



async function submitUserComment(userCommentData) {
    try {
        const response = await axios.post(
            'http://localhost:8080/comments/create',
            userCommentData, {
                withCredentials: true,
            }
        );
        console.log("Response:", response);
        if (response.status === 201) {          
            console.log("Saving User Comment");
            return "Success"
        } else {
            return ("User Commenting failed. Please try again");
        }
    } catch (error) {
        return ("An error occurred. Please try again", error);
    }
}


async function fetchCommentsByMovieReviewId(movieReviewId) {
    console.log("movieReviewId: ", movieReviewId);
    
    try {
        const response = await axios.get(`http://localhost:8080/comments/review/${movieReviewId}`, { withCredentials: true });


        if (response.status === 200) {
            const reviewCommentList = response.data;
            console.log('Review Comment List: ', reviewCommentList);
            return reviewCommentList;
        } else {
            return "Review comments not found or error occurred. Please try again";
        }
    } catch (error) {
        console.log("Error: ", error);
        return "An error occurred. Please try again";
    }
};

async function fetchCommentByUserIdAndMovieReviewId(id, userId) {
    try {
        const response = await axios.get(`http://localhost:8080/replies/edit/${id}/${userId}`, { withCredentials: true });

        if (response.status === 200) {
            const reviewData = response.data;
            console.log('Comment data:', reviewData);
            return reviewData;
        } else {
            return "Comment not found or error occurred. Please try again";
        }
    } catch (error) {
        console.log("Error: ", error);
        return "An error occurred. Please try again";
    }
};


async function updateComment(updatedData) {
    console.log("is it getting here...");
    console.log("userId param: ", updatedData.userId);
    console.log("id param: ", updatedData.id);
    try {
        console.log("Updating comment with data:", updatedData);
      
    
        const response = await axios.put(
            `http://localhost:8080/comments/edit/${updatedData.id}/${updatedData.userId}`,
            updatedData, {
                withCredentials: true,
            }
        );
        console.log("Response:", response);
        if (response.status === 200) {
            console.log("Updating comment");
            return "Success"
        } else {
            return ("Comment edit failed. Please try again");
        }
    } catch (error) {
        return ("An error occurred. Please try again", error);
    }
}


async function deleteComment(comment) {
    console.log("Is it reaching the comment service?");
    console.log("deleting comment: ", comment);
    console.log("comment id:", comment.id);
   

    try {
        const response = await axios.delete(
            `http://localhost:8080/comments/delete/${comment.id}`, 
            { 
                withCredentials: true,
            }
        );
        console.log("Response: ", response);

        if (response.status === 200 ){
            console.log("Deleting Comment");
            return "Success"
        } else {
            return ("Comment deletion failed. Please try again.");
        }
    } catch (error) {
        return ("An error occurred. Please try again ", error);
    }
}



export { submitUserComment, fetchCommentsByMovieReviewId, fetchCommentByUserIdAndMovieReviewId, updateComment, deleteComment };