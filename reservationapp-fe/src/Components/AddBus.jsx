import React, { useState } from 'react'
import "../Styles/AddBus.css"
import axios from 'axios'

const AddBus = () => {
    let [bus_name,setBus_name]=useState("")
    let [bus_no,setBus_no]=useState("")
    let [from_location,setFrom_Location]=useState("")
    let [to_location,setTo_location]=useState("")
    let [no_of_seats,setNo_Of_Seats]=useState("")
    let [date_of_departure,setDate_Of_departure]=useState("")
    let [cost_per_seat,setCostPerSeat]=useState("")
    let [available_seats,setAvaiableSeats]=useState("")

    
    let admin=JSON.parse(localStorage.getItem("Admin"))
    
    console.log(admin);
    console.log(admin.id);
    console.log(typeof(admin));
    let busData={
        bus_name,bus_no,from_location,to_location,date_of_departure,no_of_seats,admin_id:admin,cost_per_seat,available_seats
    }
    function addBusData(e){
        e.preventDefault()
        axios.post(`http://localhost:8080/api/bus/${admin.id}`,busData)
        .then((res)=>{
            console.log(res);
            alert("Bus Details have been stored")
        })
        .catch((err)=>{
            console.log(err);
            alert("Invalid Data Format")

        })
    }

  return (
    <div className="AddBus">
        <form onSubmit={addBusData} action=""> 
            <label htmlFor="">Bus Name</label>
            <input type="text" placeholder='Enter the Bus Name' required value={bus_name} onChange={(e)=>setBus_name(e.target.value)} />
            <label htmlFor="">Bus No</label>
            <input type="text" placeholder='Enter the Bus No' required value={bus_no} onChange={(e)=>setBus_no(e.target.value)} />
            <label htmlFor="">From Location</label>
            <input type="text" required placeholder='Enter the From Location ' value={from_location} onChange={(e)=>setFrom_Location(e.target.value)} />
            <label htmlFor="">To Location</label>
            <input type="text" placeholder='Enter the To Location' value={to_location} onChange={(e)=>setTo_location(e.target.value)} />
            <label htmlFor="">No of Seats</label>
            <input type="text" required placeholder='No of Seats' value={no_of_seats} onChange={(e)=>setNo_Of_Seats(e.target.value)} />
            <label htmlFor="">Date Of Departure</label>
            <input type="text" required placeholder='Date Of Departure' value={date_of_departure} onChange={(e)=>setDate_Of_departure(e.target.value)} />
            <label htmlFor="">Cost per seats</label>
            <input type="text" required placeholder='Cost per person' value={cost_per_seat} onChange={(e)=>setCostPerSeat(e.target.value)}/>
            <label htmlFor="">Available Seats</label>
            <input type="text" required placeholder='Cost per person' value={available_seats} onChange={(e)=>setAvaiableSeats(e.target.value)}/>
            <button className='btn btn-danger'>Add Bus</button>
        </form>
        
    </div>
  )
}

export default AddBus