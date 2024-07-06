import React from 'react'
import { Navigate } from 'react-router-dom'
export default function UserProtect({Child}){

    
    let x=localStorage.getItem("User")

function userVerify(){
    if(x!=null){
        return true

    }
    else{
        return false
    }

}
return(
    <div>
        {userVerify()?<Child/>:<Navigate to='/userlogin'/>}
        
    </div>
)
}