import React, { useState } from 'react'
import "../Styles/UserLoginPage.css"
import { Link, useNavigate } from 'react-router-dom'
import axios from 'axios'
const UserLoginPage = () => {

  let [email,setEmail]=useState()
  let [password,setPassword]=useState()

  let Navigate=useNavigate()

  function verify(e){
    e.preventDefault()
    axios.post(`http://localhost:8080/api/user/verifyByEmailAndPassword?email=${email}&password=${password}`)
    .then((res)=>{
      Navigate('/userHomePage')
      alert("data has been verified")
      console.log(res.data.data);
      localStorage.setItem("User",JSON.stringify(res.data.data))
    })
    .catch((err)=>{
      alert("Invalid data")
    })
  }

  return (
    <div className='UserLogin'>
        <form onSubmit={verify} action="">
            <label htmlFor="">
                Email Id
            </label>
            <input type="email" placeholder='Enter the Email id' required value={email} onChange={(e)=>{setEmail(e.target.value)}}/>
            <label htmlFor="">
                Password
            </label>
            <input type="text" placeholder='Enter the Password' required value={password} onChange={(e)=>{setPassword(e.target.value)}}/>
            <button className='btn btn-secondary '>Login</button>
        </form>
        <p>New User? <Link to={"/AdminSignUp"}>Sign Up</Link></p>
        <Link to={"/forgotPassword"}>ForgotPassword?</Link>
    </div>
  )
}

export default UserLoginPage