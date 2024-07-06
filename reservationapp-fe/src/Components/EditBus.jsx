import axios from 'axios'
import React, { useEffect } from 'react'
import { useState } from 'react'
import { useParams } from 'react-router-dom'
export default function EditBus() {
    let [bus_name, setBus_name] = useState("")
    let [bus_no, setBus_no] = useState("")
    let [from_location, setFrom_Location] = useState("")
    let [to_location, setTo_location] = useState("")
    let [no_of_seats, setNo_Of_Seats] = useState("")
    let [date_of_departure, setDate_Of_departure] = useState("")

    let params = useParams()

    useEffect(() => {
        axios.get(`http://localhost:8080/api/bus/findByBusId/${params.id}`)
        
            .then((res) => {
                console.log(params.id);
                console.log(res.data.data);
                setBus_name(res.data.data.bus_name)
                setBus_no(res.data.data.bus_no)
                setDate_Of_departure(res.data.data.date_of_departure)
                setFrom_Location(res.data.data.from_location)
                setTo_location(res.data.data.to_location)
                setNo_Of_Seats(res.data.data.no_of_seats)
            },[params.id])


    }, [])
    let busData = {
        bus_name, bus_no, from_location, to_location, date_of_departure, no_of_seats
    }

    function editBus(e) {
        e.preventDefault()

        axios.put(`http://localhost:8080/api/bus/${params.id}`, busData)
            .then((res) => {
                console.log(res);
                alert("Bus details have been edited")
            })
            .catch((err) => {
                console.log(err);
                alert("Invalid data format")
            })
    }

    return (
        <div className='EditBus'>
            <form onSubmit={editBus} action="">
                <label htmlFor="">Bus Name</label>
                <input type="text" placeholder='Enter the Bus Name' required value={bus_name} onChange={(e) => setBus_name(e.target.value)} />
                <label htmlFor="">Bus No</label>
                <input type="text" placeholder='Enter the Bus No' required value={bus_no} onChange={(e) => setBus_no(e.target.value)} />
                <label htmlFor="">From Location</label>
                <input type="text" required placeholder='Enter the From Location ' value={from_location} onChange={(e) => setFrom_Location(e.target.value)} />
                <label htmlFor="">To Location</label>
                <input type="text" placeholder='Enter the To Location' value={to_location} onChange={(e) => setTo_location(e.target.value)} />
                <label htmlFor="">No of Seats</label>
                <input type="text" required placeholder='No of Seats' value={no_of_seats} onChange={(e) => setNo_Of_Seats(e.target.value)} />
                <label htmlFor="">Date Of Departure</label>
                <input type="text" required placeholder='Date Of Departure' value={date_of_departure} onChange={(e) => setDate_Of_departure(e.target.value)} />

                <button className='btn btn-danger' >Edit Bus</button>
            </form>
        </div>
    )
}
