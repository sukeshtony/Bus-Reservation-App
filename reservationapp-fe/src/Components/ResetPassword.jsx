import React, { useState } from 'react'
import "../Styles/ResetPassword.css"


const ResetPassword = () => {
    let [newPassword,setNewPassword]=useState("")
    let [confrimPassword,setConfrimPassword]=useState("")

    function reset(newPassword,confrimPassword){
        if(newPassword===confrimPassword ){
            if(newPassword.length<8){
                
            }{
                alert("Password need to be atleast 8 ")
            }
        }else{
            alert("Both password need to be the same")
        }

    }
  return (
    <div className='ResetPassword-container'>
    <div className='ResetPassword'>
        <p>Enter New Password</p>
        <input type="password" required value={newPassword} onChange={(e)=>{setNewPassword(e.target.value)}} placeholder='Enter new password'/>
        <p>Confrim New Password</p>
        <input type="password" required value={confrimPassword} onChange={(e)=>{setConfrimPassword(e.target.value)}} placeholder='Confrim password' />
        <button  onClick={()=>{reset(newPassword,confrimPassword)}}>Reset Password</button>
    </div>
    </div>
  )
}

export default ResetPassword