import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Route,Routes } from 'react-router-dom';
import LandingPage from './Components/LandingPage';

import AdminLoginPage from "./Components/AdminLoginPage"
import UserLoginPage from './Components/UserLoginPage';
import AdminSignUp from './Components/AdminSignUp';
import UserSignUp from './Components/UserSignUp';
import AdminHomePage from './Components/AdminHomePage';
import PageNotFound from './Components/PageNotFound';
import Protect from './Components/Protect';
import { ForgotPassword } from './Components/ForgotPassword';
import ResetPassword from './Components/ResetPassword';
import UserHomePage from "./Components/UserHomePage"
import UserProtect from './Components/UserProtect';
function App(){
    return(
        <div className='App'>
            <BrowserRouter>
                <Routes>
                    <Route path='/*' element={<PageNotFound/>}/>
                    <Route path="/" element={<LandingPage/>}/>
                    <Route path="/adminlogin" element={< AdminLoginPage/>}/>                  
                    <Route path="/AdminSignUp" element={<AdminSignUp/>}/>                    
                    <Route path='/AdminHomePage/*' element={<Protect Child={AdminHomePage}/>}/>
                    <Route path='/forgotPassword/*' element={<ForgotPassword/>}/>                   
                    <Route path='/resetPassword' element={<ResetPassword/>}/>
                    <Route path='/logOut' element={<LandingPage/>}/>
                    <Route path="/userlogin" element={<UserLoginPage/>}/>
                    <Route path="/UserSignUp" element={<UserSignUp/>}/>
                    <Route path='/userHomePage/*' element={<UserProtect Child={UserHomePage}/>}/>
        
                </Routes>
            </BrowserRouter>
        </div>
    )
}
export default App