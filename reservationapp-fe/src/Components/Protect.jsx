import React from 'react'
import { Navigate } from 'react-router-dom'
export default function Protect({Child}){

    let x=localStorage.getItem("Admin")
    
function adminVerify(){
    if(x!=null){
        return true

    }
    else{
        return false
    }

}

return(
    <div>
        {adminVerify()?<Child/>:<Navigate to='/adminlogin'/>}
        
        
    </div>
)
}