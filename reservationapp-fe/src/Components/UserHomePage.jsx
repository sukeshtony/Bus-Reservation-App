import React from 'react'
import UserNavbar from './UserNavbar'
import AdminDashBoard from './AdminDashBoard'
import ViewBus from './ViewBus'
import { Route, Routes } from 'react-router-dom'

const UserHomePage = () => {
  return (
    <div className='userHomePage'>
        <UserNavbar/>
        <Routes>
            <Route path='/' element={<AdminDashBoard/>}/>
            <Route path='/viewbus' element={<ViewBus/>}/>
            
            
        </Routes>
    </div>
  )
}

export default UserHomePage