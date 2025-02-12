import React from 'react'
import { Avatar } from '@mui/material';
import { useAuth } from '../../Services/AuthContext';

function AvatarHeader({ firstname, lastname, styling }) {

   const { user } = useAuth();

    const getInitials = (name) => {
        const names = name.split(" ");
        return names.map((n) => n[0]).join("").toUpperCase();
      };

  return (
    <div>
       <Avatar className={ styling }>
          <span className="avatar-initials">
          {getInitials( firstname + " " + lastname)}
          </span>
          </Avatar>
    </div>
  )
}

export default AvatarHeader
