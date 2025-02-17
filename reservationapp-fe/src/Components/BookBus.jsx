import React, { useState,useEffect } from 'react'
import { useParams } from 'react-router-dom';
import axios from 'axios';
import "../Styles/BookBus.css"
export default function BookBus() {
    let params = useParams()
    let [bus,setbus] = useState("")
    useEffect(() => {
        axios.get(`http://localhost:8080/api/bus/findByBusId/${params.id}`)
          .then((res) => {
            console.log(res);
            setbus(res.data.data)
          })
          .catch((err) => {
            console.log(err);
          })
      }, [])
    
      let seats = [1,2,3,4,5,6,7,8,9,10]
  return (
    <div className='diplay_book'>
     <h1>{bus.bus_name}</h1>
        <b>From:</b><span>{bus.from_location}</span> <br /><br />
        <b>To:</b><span>{bus.to_location}</span><br /><br />
        <b>Coach Type : </b>
        <select >
            <option >AC</option>
            <option >Non/Ac</option>
            <option >Sleeper Ac</option>
            <option >Sleeper Non/Ac</option>

        </select>
        <br /><br />
        <b>Number Of Seats Available : </b><big><b>{bus.no_of_seats}</b></big>
        <br /><br />
        <b>Select Number Of Seats :</b><select > 
            {seats.map((seat)=>{
                return(
                    <option >{seat}</option>
                )
            })}
        </select>

            <h3>Date Of Departure : <i>{bus.date_of_departure}</i></h3>
            <button className='btn btn-danger my-2 mx-5'>Book Bus</button>
        </div>
  )
}