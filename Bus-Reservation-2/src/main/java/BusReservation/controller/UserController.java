package BusReservation.controller;

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

import BusReservation.dto.ResponseStructure;
import BusReservation.dto.UserRequest;
import BusReservation.dto.UserResponse;
import BusReservation.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(@Valid @RequestBody UserRequest userRequest,HttpServletRequest request){
		return userService.saveUser(userRequest,request);
	}
	
	@PostMapping("/activate")
	public String activate(@RequestParam String token){
		return userService.activate(token);
	}
	
	@PutMapping(value = "/updateUser/{id}")
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@RequestBody UserRequest userRequest,@PathVariable(name = "id") int id){
		return userService.updateUser(userRequest, id);
	}
	
	@GetMapping(value = "/findById/{id}")
	public ResponseEntity<ResponseStructure<UserResponse>> findById(@PathVariable(name = "id") int id ){
		return userService.findById(id);
	}
	
	@PostMapping(value = "/verifyByPhoneAndPassword")
	public ResponseEntity<ResponseStructure<UserResponse>> verifyByPhoneAndPassword(@RequestParam(name = "phone") long phone, @RequestParam(name = "password") String password ){
		return userService.verifyByPhoneAndPassword(phone, password);
	}
	
	@PostMapping(value = "/verifyByEmailAndPassword")
	public ResponseEntity<ResponseStructure<UserResponse>> verifyByEmailAndPassword(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password ){
		return userService.verifyByEmailAndPassword(email, password);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteUser(@PathVariable int id){
		return userService.deleteUser(id);
	}
}
