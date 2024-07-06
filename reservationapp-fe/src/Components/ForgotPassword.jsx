import axios from 'axios'
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import '../Styles/ForgotPassword.css'

export const ForgotPassword = () => {
  let Navigate = useNavigate()
  let [email, setEmail] = useState("")

  function verifyEmail(email) {
    axios.post(`http://localhost:8080/api/admin/forgot-password?email=${email}`)
      .then((res) => {
        alert("Mail got verified")
      })
      .catch((err) => {
        alert("There is something wrong with the server")
        console.log(err);
      })
  }

  return (
    <div className='forgot-password-section'>
    <div className="forgot-password-container">
      <h1>ENTER THE EMAIL ID</h1>
      <input type="email" required value={email} onChange={(e) => { setEmail(e.target.value) }} />
      <button className='btn btn-danger' onClick={() => { verifyEmail(email) }}>Reset Password</button>
    </div>
    </div>
  )
}

