import React from 'react'
import UserService from '../services/UserService';
import { useNavigate } from 'react-router-dom';

const User = ({user, deleteUser}) => {

    const navigate = useNavigate();
    const editUser = (e,id) => {
        e.preventDefault();
        navigate(`/editUser/${id}`);
    }

  return (
    <tr key={user.id}>
        <td className='text-left px-6 py-4 whitespace-nowrap'>
            <div className='text-sm text-gray-500'>{user.firstName}</div>
        </td>
        <td className='text-left px-6 py-4 whitespace-nowrap'>
            <div className='text-sm text-gray-500'>{user.lastName}</div>
        </td>
        <td className='text-left px-6 py-4 whitespace-nowrap'>
            <div className='text-sm text-gray-500'>{user.emailId}</div>
        </td>
        <td className='text-left px-6 py-4 whitespace-nowrap font-medium text-sm'>
            <a onClick={(e,id) => editUser(e,user.id)} className='text-indigo-600 hover:text-indigo-800 px-4 hover:cursor-pointer'>Edit</a>
            <a onClick={(e,id) => deleteUser(e,user.id)} className='text-indigo-600 hover:text-indigo-800 px-4 hover:cursor-pointer'>Delete</a>
        </td>
    </tr>
  )
}

export default User