import React from 'react'

const ReplyCard = ({ userReply }) => {
  return (
    <div>
      <p> { userReply.username } : { userReply.userReply }</p>
    </div>
  )
}

export default ReplyCard
