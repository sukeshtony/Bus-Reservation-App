package BusReservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BusReservation.dao.AdminDao;
import BusReservation.dao.UserDao;
import BusReservation.model.Admin;
import BusReservation.model.User;
import BusReservation.util.AccountStatus;
import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;
import static BusReservation.util.ApplicationConstant.Admin_Activation_Link;
import static BusReservation.util.ApplicationConstant.User_Activation_Link;
import static BusReservation.util.ApplicationConstant.ADMIN_RESET_PASSWORD_LINK;;
@Service
public class LinkGenerationService {

	@Autowired
	private AdminDao adminDao;
	@Autowired
	private UserDao userDao;
	
	public String getAdminActivationLink(Admin admin,HttpServletRequest request) {
		admin.setToken(RandomString.make(10));
		admin.setStatus(AccountStatus.INACTIVE.toString());
		adminDao.saveAdmin(admin);
		String siteUrl=request.getRequestURL().toString();
		return siteUrl.replace(request.getServletPath(), Admin_Activation_Link+admin.getToken());
		
	}
	
	public String getUserActivationLink(User user,HttpServletRequest request) {
		user.setToken(RandomString.make(30));
		user.setStatus(AccountStatus.INACTIVE.toString());
		userDao.SaveUser(user);
		String siteUrl=request.getRequestURL().toString();
		return siteUrl.replace(request.getServletPath(), User_Activation_Link+user.getToken());
	}

	public String getAdminResetPasswordLink(Admin admin,HttpServletRequest request) {
		admin.setToken(RandomString.make());
		adminDao.saveAdmin(admin);
		String siteUrl=request.getRequestURL().toString();
		return siteUrl.replace(request.getServletPath(), ADMIN_RESET_PASSWORD_LINK+admin.getToken());
	}

}
