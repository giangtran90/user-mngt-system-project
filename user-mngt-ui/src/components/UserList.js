import React, { useEffect, useState } from 'react'
import UserService from '../services/UserService';
import User from './User';
import { useNavigate } from 'react-router-dom';

const UserList = () => {

  const [loading, setLoading] = useState(true);
  const [users, setusers] = useState(null);

  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const response = await UserService.getAllUsers();
        setusers(response.data);
      } catch (error) {
        console.log(error);
      }
      setLoading(false);
    };

    fetchData();
  }, [])
  
  // xu ly su kien delete
  const deleteUser = (e,id) => {
    e.preventDefault();
    UserService.deleteUser(id).then((response) => {
      if (users) {
        setusers((prevUser) => {
          return prevUser.filter((user) => user.id !== id);
        })
      }
    })
  }

  return (
    <div className='container mx-auto my-8'>
      <div className='h-12'>
        <button onClick={() => navigate("/addUser")} className='rounded bg-slate-600 text-white px-6 py-2 font-semibold'>Add User</button>
      </div>
      <div className='flex shadow border-b'>
          <table className='min-w-full'>
            <thead className='bg-gray-100'>
                <tr>
                <th className='text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>First Name</th>
                <th className='text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>Last Name</th>
                <th className='text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>Email</th>
                <th className='text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>Actions</th>
                </tr>
            </thead>
            {!loading && (
            <tbody className='bg-white'>
              {users.map((user) => (
                <User user={user} deleteUser = {deleteUser} key={user.id}></User>
              ))}
            </tbody>
            )}
          </table>
      </div>
    </div>
  )
}

export default UserList