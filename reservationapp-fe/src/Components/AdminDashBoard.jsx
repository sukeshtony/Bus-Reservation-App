import React, { useState } from 'react'
import "../Styles/AdminDashBoard2.css"
import axios from 'axios'
import { useNavigate } from 'react-router-dom'

const AdminDashBoard = () => {
  let [from_location, setFrom_Location] = useState("")
  let [to_location, setTo_location] = useState("")
  let [date_of_departure, setDate_Of_departure] = useState("")
  let Navigate = useNavigate()
  let [buses, setBuses] = useState([])

  function searchBus(e) {
    e.preventDefault()
    axios.get(`http://localhost:8080/api/bus/find?from=${from_location}&to=${to_location}&date=${date_of_departure}`)
      .then((res) => {
        console.log(res.data);
        setBuses(res.data.data)
      })
      .catch((err) => {
        console.log("Bus is Not available");
        alert("bus is not available")
      })
  }

  function bookbus(id){
    Navigate(`/AdminHomePage/bookbus/${id}`)
  }
  return (
    <div className='AdminDashBoard'>
      <form action="" onSubmit={searchBus}>
        <input type="text" required placeholder='Enter the from location' value={from_location} onChange={(e) => { setFrom_Location(e.target.value) }} />
        <input type="text" required placeholder='Enter the to location' value={to_location} onChange={(e) => { setTo_location(e.target.value) }} />
        <input type="text" required placeholder='Enter the date' value={date_of_departure} onChange={(e) => { setDate_Of_departure(e.target.value) }} />
        <button>Search</button>
      </form>
      
      {buses.length > 0 && (
        <div className='bus_list'>
          <div className='bus_header'>
            <p>Bus Name</p>
            <p>Bus Number</p>
            <p>No of Seats</p>
            <p>Departure</p>
            <p>Arrival</p>
            <p>Book</p>
          </div>
          {buses.map((item, index) => {
            return (
              <div key={index} className='bus_item'>
                <h4>{item.bus_name}</h4>
                <p>{item.bus_no}</p>
                <i>{item.no_of_seats}</i>
                <p>{item.from_location}</p>
                <p>{item.to_location}</p>
                <button className='btn btn-danger' onClick={()=>{bookbus(item.id)}}>Book Bus</button>
              </div>
            );
          })}
        </div>

      )}
      <br />
      <br />
      
         <ul  class="footer_links">
        <li class="footer_link_section main_footer_item">

            <div class="footer_main_logos">
                 <picture>
                    <h1><i>Tony<sup>.in</sup></i></h1>
                </picture>
            </div>
            <span class="footer_text">Tony.in is the world's largest online bus ticket booking service trusted by over 25 million happy customers globally. Tony.in offers bus ticket booking through its website, iOS and Android mobile apps for all major routes.</span>
        </li>
        <li class="footer_link_section" id="about_redbus_title_footer">
            <h3 class="footer_link_section_title">About</h3>
                    <a class="footer_link_section_item footer_link_new" href="https://www.redbus.in/info/aboutus" id="about_us_footer" target="_blank">
                        About us
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://www.redbus.in/info/Investors" id="investor_relations_footer" target="_blank">
                        Investor Relations
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://www.redbus.in/info/contactus" id="contact_us_footer" target="_blank">
                        Contact us
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://m.redbus.in/" id="mobile_version_footer" target="_blank">
                        Mobile version
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://www.redbus.in/info/mobile" id="redbus_on_bus_footer" target="_blank">
                        redBus on mobile
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://www.redbus.in/sitemap.html" id="sitemap_footer" target="_blank">
                        Sitemap
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://www.redbus.in/info/offerTerms" id="offers_footer" target="_blank">
                        Offers
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://www.redbus.in/careers" id="careers_footer" target="_blank">
                        Careers
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://www.redbus.in/values" id="values_footer" target="_blank">
                        Values
                    </a>
        </li>
        <li class="footer_link_section" id="info_title_footer">
            <h3 class="footer_link_section_title">Info</h3>
                    <a class="footer_link_section_item footer_link_new" href="https://www.redbus.in/info/termscondition" id="terms_n_conditions_footer" target="_blank">
                        T&amp;C
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://www.redbus.in/info/privacypolicy" id="privacy_policy_footer" target="_blank">
                        Privacy policy
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://www.redbus.in/info/faq" id="faq_footer" target="_blank">
                        FAQ
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://blog.redbus.in" id="blog_footer" target="_blank">
                        Blog
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://onboardvendor.redbus.in/" id="bus_operator_registration_footer" target="_blank">
                        Bus operator registration
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://in3.seatseller.travel/" id="agent_registration_footer" target="_blank">
                        Agent registration
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://www.acko.com/" id="insurance_partner_footer" target="_blank">
                        Insurance partner
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://www.redbus.in/info/useragreement" id="user_agreement_footer" target="_blank">
                        User agreement
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://www.redbus.in/primo/primo-service" target="_blank">
                        Primo Bus
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://www.redbus.in/bus-timetable" target="_blank">
                        Bus Timetable
                    </a>
        </li>
        <li class="footer_link_section" id="global_sites_title_footer">
            <h3 class="footer_link_section_title">Global Sites</h3>
                    <a class="footer_link_section_item footer_link_new" href="https://www.redbus.in/" id="India_site_footer" target="_blank">
                        India
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://www.redbus.sg/" id="singapore_site_footer" target="_blank">
                        Singapore
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://www.redbus.my/" id="malaysia_site_footer" target="_blank">
                        Malaysia
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://www.redbus.id/" id="indonesia_site_footer" target="_blank">
                        Indonesia
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://www.redbus.pe/" id="peru_site_footer" target="_blank">
                        Peru
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://www.redbus.co/" id="colombia_site_footer" target="_blank">
                        Colombia
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://www.redbus.com.kh/" id="cambodia_site_footer" target="_blank">
                        Cambodia
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://www.redbus.vn/" id="vietnam_site_footer" target="_blank">
                        Vietnam
                    </a>
        </li>
        <li class="footer_link_section" id="our_partners_title_footer">
            <h3 class="footer_link_section_title">Our Partners</h3>
                    <a class="footer_link_section_item footer_link_new" href="https://www.goibibo.com/bus/" id="goibibo_bus_footer" target="_blank" rel="nofollow">
                        Goibibo Bus
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://www.goibibo.com/hotels/" id="goibibo_hotels_footer" target="_blank" rel="nofollow">
                        Goibibo Hotels
                    </a>
                    <a class="footer_link_section_item footer_link_new" href="https://www.makemytrip.com/hotels/" id="makemytrip_hotels_footer" target="_blank" rel="nofollow">
                        Makemytrip Hotels
                    </a>
        </li>
    </ul> 
      
    
    </div>
    
  )
}

export default AdminDashBoard