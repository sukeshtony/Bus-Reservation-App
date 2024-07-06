import React, { useState } from 'react'
import "../Styles/UserSignUp.css"
import { useNavigate } from 'react-router-dom'
import axios from 'axios'

const UserSignUp = () => {
    let [name,setName]=useState("")
    let [email,setEmail]=useState("")
    let [gender,setGender]=useState("")
    let [phone,setPhone]=useState("")
    let [password,setPassword]=useState("")
    let [age,setAge]=useState("")
    
    let data={
      name,email,phone,gender,age,password
    }

    let Navigate=useNavigate()
    function createUser(e){
        e.preventDefault()
        axios.post("http://localhost:8080/api/user",data)
        .then((res)=>{
          Navigate("/adminlogin")
          alert("Message has sent to the gmail . Click activate to activate your account")
          console.log(res);
          
        })
        .catch((err)=>{
          alert("Invalid data")
          console.log(err);
        })
    }
  return (
    
    <div className='UserSignUp'>
        <form className='UserSignUp' onSubmit={createUser} action="">
            <label htmlFor="">Name</label>
            <input type="text" placeholder='Enter the Name' required value={name} onChange={(e)=>setName(e.target.value)} />
            <label htmlFor="">Email</label>
            <input type="text" placeholder='Enter the Email' required value={email} onChange={(e)=>setEmail(e.target.value)} />
            <label htmlFor="">Age</label>
            <input type="text" placeholder='Enter the Age' required value={age} onChange={(e)=>setAge(e.target.value)} />
            <label htmlFor="">Gender</label>
            <input type="text" required placeholder='Enter the Genter ' value={gender} onChange={(e)=>setGender(e.target.value)} />
            <label htmlFor="">Phone</label>
            <input type="text" placeholder='Enter the Phone' value={phone} onChange={(e)=>setPhone(e.target.value)} />
            <label htmlFor="">Password</label>
            <input type="text" required placeholder='Enter the Password' value={password} onChange={(e)=>setPassword(e.target.value)} />
            <button>Register</button>
            </form> 
    </div>
  )
}

export default UserSignUp