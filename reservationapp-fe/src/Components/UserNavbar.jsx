import React from 'react'
import "../Styles/UserNavbar.css"
import UserDropDown from './UserDropDown'
const UserNavbar = () => {
  return (
    <div className='UserNavbar'>
        <div className='logo'>
            <h1><i>Tony</i><sup><i>.in</i></sup></h1>
        </div>
        <div className='options'>
            <UserDropDown/>
        </div>
    </div>
  )
}

export default UserNavbar