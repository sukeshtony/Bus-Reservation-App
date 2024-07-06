import React from 'react'
import AdminDropDown from './AdminDropDown'
import "../Styles/AdminNavbar.css"

const AdminNavbar = () => {
  return (
    <div className='AdminNavbar'>
        <div className='logo'>
            <h1><i>Tony</i><sup><i>.in</i></sup></h1>
        </div>
        <div className='options'>
            <AdminDropDown/>
        </div>
    </div>
  )
}

export default AdminNavbar