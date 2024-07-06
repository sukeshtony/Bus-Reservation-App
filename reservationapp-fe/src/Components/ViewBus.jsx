import React, { useEffect, useState } from 'react';
import axios from 'axios';
import "../Styles/ViewBus.css";
import { useNavigate } from 'react-router-dom';

const ViewBus = () => {
    let [bus, setBus] = useState([]);
    let navigate = useNavigate();
    let [reload, setReload] = useState(true);

    useEffect(() => {
        axios.get(`http://localhost:8080/api/bus/findAll`)
            .then((res) => {
                console.log("Hello");
                console.log(res.data.data);
                setBus(res.data.data);
                setReload(true);
            })
            .catch((err) => {
                console.log(err);
            });
    }, [reload]);

    function removeBus(bus_id, bus_no) {
        axios.delete(`http://localhost:8080/api/bus/${bus_id}`)
            .then(() => {
                setReload(false)
                console.log(`bus_n0 ${bus_no} has been removed`);
            })
            .catch((err) => {
                console.log(err);
            });
    }

    function editBus(bus_id) {
        console.log(bus_id);
        navigate(`/AdminHomePage/editBus/${bus_id}`);
    }

    return (
        <div className='viewBus'>
            {bus.map((item) => (
                <div className="bus_Details" key={item.id}>
                    <div className="bus_info">
                        <p>{item.bus_name}</p>
                        <p>{item.from_location} - {item.to_location}</p>
                        <p>{item.date_of_departure}</p>
                        <i>{item.no_of_seats} seats</i>
                        <p>{item.bus_no}</p>
                    </div>
                    <div className="bus_actions">
                        <button onClick={() => editBus(item.id)}>Edit</button>
                        <button onClick={() => removeBus(item.id, item.bus_no)}>Delete</button>
                    </div>
                </div>
            ))}
        </div>
    );
};

export default ViewBus;
