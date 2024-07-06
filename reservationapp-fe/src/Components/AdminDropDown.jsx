import React from 'react'
import Dropdown from 'react-bootstrap/Dropdown';
import "../Styles/AdminDropDown.css"
import { Navigate, useNavigate } from 'react-router-dom';
const AdminDropDown = () => {
  let x=localStorage.getItem("Admin")
  let Navigate=useNavigate()
  function logOut(){
    x=null;
    localStorage.setItem("Admin",x)
    Navigate("/logOut")
  }
  return (
    <div className='AdminDropDown'>
        <Dropdown>
      <Dropdown.Toggle variant="success" id="dropdown-basic" color='#ff6f61  '>
        Account
      </Dropdown.Toggle>

      <Dropdown.Menu >
        <Dropdown.Item href="/AdminHomePage/addbus">Add Bus</Dropdown.Item>
        <Dropdown.Item href="/AdminHomePage/viewbus">Buses List</Dropdown.Item>
        {/* <Dropdown.Item href="ww">Edit Profile</Dropdown.Item> */}
        <Dropdown.Item href="#/action-3" onClick={logOut } >Log out</Dropdown.Item> 
      </Dropdown.Menu>
    </Dropdown>
    </div>
  )
}

export default AdminDropDown