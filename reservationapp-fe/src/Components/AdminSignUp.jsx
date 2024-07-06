import React, { useState } from 'react'
import "../Styles/AdminSignUp.css"
import axios from 'axios'
import { useNavigate } from 'react-router-dom'
const AdminSignUp = () => {
    let [name,setName]=useState("")
    let [email,setEmail]=useState("")
    let [phone,setPhone]=useState("")
    let [gst_no,setGst_no]=useState("")
    let [travels_name,setTravels_name]=useState("")
    let [password,setPassword]=useState("")
    let navigate=useNavigate()
    let data={
      name,email,password,phone,gst_no,travels_name,password
    }
    function createAdmin(e){
        e.preventDefault()
        axios.post("http://localhost:8080/api/admin",data)
        .then((res)=>{
          navigate("/adminlogin")
          alert("Message has sent to the gmail . Click activate to activate your account")
          console.log(res);
          
        })
        .catch((err)=>{
          alert("Invalid data")
          console.log(err);
        })
    }
  return (
    <div className='AdminSignUp'>
        <form onSubmit={createAdmin} action="">
            <label htmlFor="">Name</label>
            <input type="text"  placeholder='Enter the Name' required value={name} onChange={(e)=>{setName(e.target.value)}} />
            <label htmlFor="">Email</label>
            <input type="email" placeholder='Enter the Email' required value={email} onChange={(e)=>{setEmail(e.target.value)}} />
            <label htmlFor="">Phone</label>
            <input type="text" placeholder='Enter the Phone' required value={phone} onChange={(e)=>{setPhone(e.target.value)}} />
            <label htmlFor="">Gst_Number</label>
            <input type="text" placeholder='Enter the Gst Number' required value={gst_no} onChange={(e)=>{setGst_no(e.target.value)}} />
            <label htmlFor="">Travels Name</label>
            <input type="text" placeholder='Enter the Travels Name' required value={travels_name} onChange={(e)=>{setTravels_name(e.target.value)}} />
            <label htmlFor="">Password</label>
            <input type="password" placeholder='Enter the Password ' required value={password} onChange={(e)=>{setPassword(e.target.value)}} />
            <button>Register</button>
        </form>
    </div>
  )
}

export default AdminSignUp