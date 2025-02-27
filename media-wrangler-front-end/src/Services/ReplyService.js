import axios from "axios";


async function submitUserReply(userReplyData) {

    console.log("Is it reaching the service layer? ", userReplyData);
    try {
        const response = await axios.post(
            'http://localhost:8080/replies/create',
            userReplyData, {
                withCredentials: true,
            }
        );
        console.log("Response:", response);
        if (response.status === 201) {          
            console.log("Saving User Reply");
            return "Success"
        } else {
            return ("User Reply failed. Please try again");
        }
    } catch (error) {
        return ("An error occurred. Please try again", error);
    }
}

async function fetchRepliesByCommentId(commentId) {
    console.log("commentId: ", commentId);
    
    try {
        const response = await axios.get(`http://localhost:8080/replies/view/${commentId}`, { withCredentials: true });


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

export { submitUserReply, fetchRepliesByCommentId };