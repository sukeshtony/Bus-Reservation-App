import React from 'react'
import { Link } from 'react-router-dom'


import "../Styles/LandingPage.css"
const LandingPage = () => {
  return (
    <div className="landingpage">
        <Link to="/adminlogin">
        <img src="https://www.iaccindia.com/wp-content/uploads/2023/01/member-login-icon.png" alt="" />
        <h2>Admin</h2>
        </Link>
        <Link to="/userlogin">
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTNrcyCZGEyOpBehrpDx8qZov3ne1joNdZ0Zw&s" alt="" />
        <h2>User</h2>
        </Link>
    </div>
  )
}

export default LandingPage