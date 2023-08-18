import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import UserService from '../services/UserService';

const EditUser = () => {

    const navigate = useNavigate();
    
    const {id} = useParams();
    
    const [user, setUser] = useState({
        id : id,
        firstName : "",
        lastName : "",
        emailId : ""
    })

    const handleChange = (e) => {
        const value = e.target.value;
        setUser({...user,[e.target.name]:value});
        console.log(value);
    }

    useEffect(() => {
      const fetchData = async () => {
        try {
            const response = await UserService.getUserById(id);
            setUser(response.data)
        } catch (error) {
            console.log(error);
        }
      }
      fetchData();
    }, [])
    
    const updateUser = (e) => {
        e.preventDefault();
        UserService.editUser(user,id)
        .then((response) => {
            navigate("/userList");
        })
        .catch((error) => {
            console.log(error);
        })
    }

    const clear = (e) => {
        e.preventDefault();
        setUser({
            id : id,
            firstName : user.firstName,
            lastName : "",
            emailId : ""
        })
    }

  return (
    // max-w-2xl: chieu rong toi da, shadow: them bong duoi, border-b: duong vien duoi, mx-auto: can le tu dong
    <div className='flex max-w-2xl mx-auto shadow border-b'>
        <div className='px-5 py-5'>
            {/* font-thin text-2xl: phong chu mong va to, tracking-wider: tao khoang cach giua cac chu cai */}
            <div className='font-thin text-2xl tracking-wider'>
                <h1>Edit User</h1>
            </div>
            {/* items-center justify-center: cac muc o giua, can giua
                h-10 w-96 border mt-2 px-2 py-2: chieu cao, chieu rong, vien, magin top, pading x,y
                name='firstName' value={employee.firstName}: khi bao name va value de lay du lieu
                onChange={(e)=>handleChange(e)}: giup cap nhat trang thai
             */}
            <div className='items-center justify-center h-14 w-full my-4'>
                <label className='block text-gray-600 text-sm font-normal'>First Name</label>
                <input type='text' name='firstName' value={user.firstName} onChange={(e) => handleChange(e)} className='h-10 w-96 border mt-2 px-2 py-2'></input>
            </div>
            <div className='items-center justify-center h-14 w-full my-4'>
                <label className='block text-gray-600 text-sm font-normal'>Last Name</label>
                <input type='text' name='lastName' value={user.lastName} onChange={(e) => handleChange(e)} className='h-10 w-96 border mt-2 px-2 py-2'></input>
            </div>
            <div className='items-center justify-center h-14 w-full my-4'>
                <label className='block text-gray-600 text-sm font-normal'>Email Name</label>
                <input type='email' name='emailId' value={user.emailId} onChange={(e) => handleChange(e)} className='h-10 w-96 border mt-2 px-2 py-2'></input>
            </div>

            {/* space-x-3: tao khoang cach giua 2 button
                rounded hover:bg-green-700 => tao vien tron, khi hover mau dam hon
            */}
            <div className='items-center justify-center h-14 w-full my-4 space-x-3 pt-4'>
                <button onClick={updateUser} className='rounded text-white font-semibold bg-green-400 hover:bg-green-700 px-6 py-2'>Save</button>
                <button onClick={clear} className='rounded text-white font-semibold bg-red-400 hover:bg-red-700 px-6 py-2'>Clear</button>
                <button onClick={() => navigate("/")} className='rounded text-white font-semibold bg-blue-400 hover:bg-red-700 px-6 py-2'>Cancel</button>
            </div>
        </div>
    </div>
  )
}

export default EditUser