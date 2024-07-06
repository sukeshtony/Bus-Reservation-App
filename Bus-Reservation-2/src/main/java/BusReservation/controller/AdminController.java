package BusReservation.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import BusReservation.dto.AdminRequest;
import BusReservation.dto.AdminResponse;
import BusReservation.dto.ResponseStructure;
import BusReservation.service.AdminService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<AdminResponse>> saveAdmin(@Valid @RequestBody AdminRequest adminRequest,HttpServletRequest request){
		return adminService.saveAdmin(adminRequest, request);
	}
	
	@PostMapping("/activate")
	public String activate(@RequestParam String token){
		return adminService.activate(token);
	}
	
	@PutMapping("/updateAdmin/{id}")
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(@RequestBody AdminRequest adminRequest,@PathVariable(name = "id") int id){
		return adminService.updateAdmin(adminRequest,id);
	}
	
	@GetMapping("/findByEmail")
	public ResponseEntity<ResponseStructure<AdminResponse>> findByEmail(@RequestParam String email){
		return adminService.findByEmail(email);
	}
	
	@PostMapping("/forgot-password")
	public String forgotPassword(@RequestParam String email,HttpServletRequest request) {
		return adminService.forgotPassword(email, request);
	}
	
	@GetMapping("/reset-password")
	public void verifyResetPassword(@RequestParam String token, HttpServletRequest request, HttpServletResponse response) {
	    AdminResponse adminResponse = adminService.verifyLink(token);
	    if (adminResponse != null) {
	        try {
	            HttpSession session = request.getSession();
	            // session.setAttribute("admin", adminResponse);
	            // response.addCookie(new Cookie("admin", adminResponse.getEmail()));
	            System.out.println("Redirecting to: http://localhost:3000/resetPassword");
	            response.sendRedirect("http://localhost:3000/resetPassword");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } else {
	        try {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid or expired token");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	@GetMapping(value = "/findById/{id}")
	public ResponseEntity<ResponseStructure<AdminResponse>> findById(@PathVariable(name = "id") int id ){
		return adminService.findById(id);
	}
	
	@PostMapping(value = "/verifyAdminByPhoneAndPassword")
	public ResponseEntity<ResponseStructure<AdminResponse>> verifyByPhoneAndPassword(@RequestParam(name = "phone") long phone, @RequestParam(name = "password") String password ){
		return adminService.verifyByPhoneAndPassword(phone, password);
	}
	
	@PostMapping(value = "/verifyAdminByEmailAndPassword")
	public ResponseEntity<ResponseStructure<AdminResponse>> verifyByEmailAndPassword(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password ){
		return adminService.verifyByEmailAndPassword(email, password);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteAdmin(@PathVariable int id){
		return adminService.deleteAdmin(id);
	}
	
	
	
	
	
	
	
	
	
}
