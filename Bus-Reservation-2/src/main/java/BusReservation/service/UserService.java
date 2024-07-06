package BusReservation.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import BusReservation.dao.UserDao;
import BusReservation.dto.EmailConfiguration;
import BusReservation.dto.ResponseStructure;
import BusReservation.dto.UserRequest;
import BusReservation.dto.UserResponse;
import BusReservation.exception.AdminNotFoundException;
import BusReservation.model.User;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private LinkGenerationService linkGenerationService;
	@Autowired
	private ReservationApiMailService mailService;
	@Autowired
	private EmailConfiguration emailCon;
	
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest,HttpServletRequest request){
		ResponseStructure<UserResponse> structure=new ResponseStructure<>();
		User saveUser=mapToUser(userRequest);
		String activation_Link=linkGenerationService.getUserActivationLink(saveUser, request);
		emailCon.setToAddress(userRequest.getEmail());
		emailCon.setSubject("Activate Your Account");
		emailCon.setText("Click the link to activate your account "+activation_Link);
		structure.setMessage(mailService.sendMail(emailCon));
		structure.setData(mapToUserResponse(saveUser));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(UserRequest userRequest,int id){		
		ResponseStructure<UserResponse> structure=new ResponseStructure<>();
		Optional<User> recUser=userDao.findById(id);
		if(recUser.isPresent()) {	
			User dbuser=recUser.get();
			
			dbuser.setEmail(userRequest.getEmail());
			dbuser.setGender(userRequest.getGender());
			dbuser.setName(userRequest.getName());
			dbuser.setPassword(userRequest.getPassword());
			dbuser.setPhone(userRequest.getPhone());
			dbuser.setId(id);
			userDao.SaveUser(dbuser);
			structure.setMessage("User got updated");
			structure.setData(mapToUserResponse(dbuser));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);		
		}
		throw new AdminNotFoundException("Can't find the admin with for the given id");
		
	}
	
	public ResponseEntity<ResponseStructure<UserResponse>> findById(int id){
		ResponseStructure<UserResponse> structure=new ResponseStructure<>();
		
		Optional<User> option=userDao.findById(id);
		if(option.isPresent()) {
			structure.setMessage("User has been found");
			structure.setData(mapToUserResponse(option.get()));
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
			
		}
		throw new AdminNotFoundException("Invaild Id");
	}
	
	
	public ResponseEntity<ResponseStructure<UserResponse>> verifyByPhoneAndPassword(long phone,String password){
		ResponseStructure<UserResponse> structure=new ResponseStructure<>();
		Optional<User> option=userDao.verifyByPhoneAndPassword(phone, password);
		if(option.isPresent()) {
			structure.setMessage("User has been verified for the above phone number");
			structure.setData(mapToUserResponse(option.get()));
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AdminNotFoundException("Invaild Phone or password");
	}
	
	public ResponseEntity<ResponseStructure<UserResponse>> verifyByEmailAndPassword(String email,String password){
		ResponseStructure<UserResponse> structure=new ResponseStructure<>();
		Optional<User> option=userDao.verifyByEmailAndPassword(email, password);
		if(option.isPresent()) {
			structure.setMessage("User has been verified for the above email");
			structure.setData(mapToUserResponse(option.get()));
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AdminNotFoundException("Invaild email or password");
}
	
	public ResponseEntity<ResponseStructure<String>> deleteUser(int id){
		ResponseStructure<String> structure=new ResponseStructure<>();
		Optional<User> recUser=userDao.findById(id);
		if(recUser.isEmpty())
			throw new AdminNotFoundException("Invalid User Id");
		userDao.deleteUser(id);
		structure.setMessage("User has been deleted");
		structure.setData("Data has been removed");
		structure.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}
	
	private User mapToUser(UserRequest userRequest) {
		return User.builder().email(userRequest.getEmail()).gender(userRequest.getGender()).name(userRequest.getName())
				.password(userRequest.getPassword()).phone(userRequest.getPhone()).age(userRequest.getAge()).build();
	}
	
	private UserResponse mapToUserResponse(User user) {
		return UserResponse.builder().id(user.getId()).name(user.getName()).email(user.getEmail())
				.gender(user.getGender()).phone(user.getPhone()).age(user.getAge()).build();
	}
	
	public String activate(String token) {
		Optional<User> recUser=userDao.findByToken(token);
		if(recUser.isPresent()) {
			User dbUser=recUser.get();
			dbUser.setStatus("ACTIVE");
			dbUser.setToken(null);
			userDao.SaveUser(dbUser);
			
			return "Your Account Has Been Activated";
		}
		throw new AdminNotFoundException("Invalid Link");
	}
	
	
	
}



