import React from 'react'
import { Route, Routes } from 'react-router-dom'
import AdminDashBoard from './AdminDashBoard'
import AdminNavbar from './AdminNavbar'
import AddBus from "./AddBus"
import ViewBus from './ViewBus'
import EditBus from './EditBus'
import BookBus from './BookBus'
// import "../Styles/AdminHomePage.css"

const AdminHomePage = () => {
  return (
    <div className='AdminHomePage'>
        <AdminNavbar/>
        <Routes>
            <Route path='/' element={<AdminDashBoard/>}/>
            <Route path="/addbus" element={<AddBus/>}/>
            <Route path='/viewbus' element={<ViewBus/>}/>
            <Route path='/editBus/:id' element={<EditBus/>}/>
            <Route path='/bookbus/:id' element={<BookBus/>}/>
            
        </Routes>
    </div>
  )
}

export default AdminHomePage