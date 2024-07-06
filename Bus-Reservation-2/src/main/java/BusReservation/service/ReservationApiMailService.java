package BusReservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import BusReservation.dto.EmailConfiguration;

@Service
public class ReservationApiMailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public String sendMail(EmailConfiguration emailCon) {
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		simpleMailMessage.setTo(emailCon.getToAddress());
		simpleMailMessage.setText(emailCon.getText());
		simpleMailMessage.setSubject(emailCon.getSubject());
		
		javaMailSender.send(simpleMailMessage);
		return "Registration Successful verification mail has been send";
	}
}
