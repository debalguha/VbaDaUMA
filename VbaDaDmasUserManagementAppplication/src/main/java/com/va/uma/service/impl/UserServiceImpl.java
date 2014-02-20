package com.va.uma.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.va.uma.dao.IAccessDao;
import com.va.uma.dao.ITeamDao;
import com.va.uma.dao.IUserInfoDao;
import com.va.uma.model.Access;
import com.va.uma.model.Team;
import com.va.uma.model.UserAppAccess;
import com.va.uma.model.UserInfo;
import com.va.uma.model.UserTeamAllocationPK;
import com.va.uma.model.UserInfo.UserStatus;
import com.va.uma.model.UserTeamAllocation;
import com.va.uma.service.IUserService;

@Service("userService")
//@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserServiceImpl implements IUserService {
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
	@Autowired
	private IUserInfoDao userInfoDao;

	@Autowired
	private ITeamDao teamDao;

	@Autowired
	IAccessDao accessDefDao;

	@Override
	public void saveUser(UserInfo entity) {
		userInfoDao.save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateUser(UserInfo entity) {
		userInfoDao.update(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteUser(String userId) {
		userInfoDao.delete(userInfoDao.findById(userId));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public UserInfo getUserInfoByUsername(String username) {
		return userInfoDao.findByUsername(username);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public UserInfo getUserInfoById(String id) {
		return userInfoDao.findById(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<UserInfo> listUser(int pageSize, int pageIndex) {
		return userInfoDao.listAll();
	}

	@Override
	public void saveUserAppAccess(UserAppAccess entity) {
		userInfoDao.saveUserAppAccess(entity);
	}

	@Override
	public void deleteUserAppAccess(UserAppAccess entity) {
		userInfoDao.deleteUserAppAccess(entity);
	}

	@Override
	public void updateUserAppAccess(UserAppAccess entity) {
		userInfoDao.updateUserAppAccess(entity);
	}

	@Override
	public void deleteAllAppAccessByUserId(String userId) {
		userInfoDao.deleteAllAppAccessByUserId(userId);
	}

	@Override
	public boolean isAppAccessUsed(String appName, String accessId) {
		return userInfoDao.isAppAccessUsed(appName, accessId);
	}

	@Override
	public boolean isTeamUsed(String teamid) {
		return userInfoDao.isTeamUsed(teamid);

	}

	@Override
	public void sendEmailToUser(String id, String type) {
		UserInfo userInfo = userInfoDao.findById(id);
		String from = "rjalali2001@yahoo.com";
		String pass = "Shadi%$321";
		String to = userInfo.getEmail();
		String host = "smtp.mail.yahoo.com";

		// Get system properties
		Properties properties = System.getProperties();
		// Setup mail server
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.user", from);
		properties.put("mail.smtp.password", pass);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			if (type == "alert") {
				message.setSubject("your account status ");
				message.setText(userInfo.getAlert());
			}
			if (type == "creator") {
				message.setSubject("your password has been changed !");
				message.setText("For security reason your password has" + " been changed by Adminstrator please contact to UMAaDMIN@DNCX.COM");
			}
			if (type == "user") {
				message.setSubject("your password has been changed !");
				message.setText("your password has been Successfully changed" + " if you havent change the password  please contact immidtately to UMAaDMIN@DNCX.COM");
			}

			// Send message
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	// @Scheduled(cron="0 0 5 * * *")
	// @Scheduled(cron="*/5 * * * * ?")
	@Override
	public void updateUserStatusById() {
		List<UserInfo> list = userInfoDao.listAll();
		for (Iterator<UserInfo> iterator = list.iterator(); iterator.hasNext();) {
			UserInfo user = (UserInfo) iterator.next();
			long x = user.getLogin();
			long y = 0;
			boolean b = (x == y);
			long lastLogin = user.getLogin();
			long milliseconds1 = lastLogin;
			long milliseconds2 = Calendar.getInstance().getTimeInMillis();
			long diff = milliseconds2 - milliseconds1;
			long diffDays = diff / (24 * 60 * 60 * 1000);
			long remainDays = 30 - diffDays;
			if (!b) {
				System.out.println("inside if (!b)");
				if (diffDays > 25 && diffDays < 30) {

					user.setAlert("alert:Your Account will be inactive by :" + remainDays + "  Days.please login to your acount to" + " avoid your account become inactive ");
					try {

						sendEmailToUser(user.getId(), "alert");

					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("updateUserStatusById INSIDE " + "diffDays>30 failed send email failed");
					}
				}
				if (diffDays > 30) {
					user.setStatus(UserStatus.inactive);
					user.setAlert("$$$$$$$$$$$$$$$$$$$the  account didnt Login for:" + diffDays + "  days and now is inactive. " + "please contact to admin ");

					try {
						sendEmailToUser(user.getId(), "alert");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("updateUserStatusById INSIDE" + " diffDays>30 failed send email failed ");
					}

				}

			}

		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<UserInfo> listUserByTeam(String teamId) {
		List<UserInfo> list = userInfoDao.listUserByTeam(teamId);
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<UserInfo> getAllActiveOrInactiveUsers(UserStatus enrity) {

		List<UserInfo> list = new ArrayList<UserInfo>();
		list = userInfoDao.getAllActiveUsers(enrity);
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Collection<UserAppAccess> getAllUsersInAppX(String userId) {
		return userInfoDao.getAllUsersInAppX(userId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<UserInfo> getReport(Team teamId, String appName, UserStatus status) {
		List<UserInfo> list = new ArrayList<UserInfo>();
		list = userInfoDao.getReport(teamId, appName, status);
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveUserDetails(UserInfo user, Map<String, String> appAccessMap, Collection<Team> teams) {
		Set<UserAppAccess> accesses = new HashSet<UserAppAccess>();
		for (Map.Entry<String, String> entry : appAccessMap.entrySet()) {
			String appName = entry.getKey();
			String accessId = entry.getValue();
			UserAppAccess userAppAccess = new UserAppAccess();
			userAppAccess.setUserInfo(user);
			userAppAccess.setAppName(appName);
			userAppAccess.setAccess(accessDefDao.findById(accessId));
			accesses.add(userAppAccess);
		}
		user.setUserAppAccessList(accesses);
		createAndAttachTeamAllocations(teams, user);
		saveUser(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateUserDetails(UserInfo updatedUser, Map<String, String> appAccessMap, Collection<Team> teams) {
		logger.info("User update request has arrived (1).");
		UserInfo user = getUserInfoById(updatedUser.getId());
		if (!user.equals(updatedUser)) {
			logger.info("Core user attributes have changed. Will update UserInfo entity");
			BeanUtils.copyProperties(updatedUser, user);
		} else {
			//updatedUser = user;
			logger.warning("Core user attributes have not changed. Will not update UserInfo entity");
		}
		changeUserAppAccess(appAccessMap, user);
		updateAndSaveTeamAllocations(teams, user);
		userInfoDao.update(user);
	}

	public void setTeamDao(ITeamDao teamDao) {
		this.teamDao = teamDao;
	}
	@Transactional(propagation = Propagation.MANDATORY)
	private void changeUserAppAccess(Map<String, String> appAccessMap, UserInfo user) {
		logger.info("Creating UserAppAccess update list");
		Set<UserAppAccess> appAccesses = new HashSet<UserAppAccess>();
		Set<String> appAccessSet = appAccessMap.keySet();
		Iterator<String> appAccessIterator = appAccessSet.iterator();
		Map<String, UserAppAccess> userAppAccessMapFromUserObject = createuserAppAccessMapFromUserObject(user);
		while (appAccessIterator.hasNext()) {
			String appName = (String) appAccessIterator.next();
			String access = appAccessMap.get(appName);
			Access acces = accessDefDao.findById(access);
			UserAppAccess origUserAppAccess = userAppAccessMapFromUserObject.get(appName);
			if(!origUserAppAccess.getAccess().equals(acces))
				origUserAppAccess.setAccess(acces);
			appAccesses.add(origUserAppAccess);
		}
	}
	@Transactional(propagation = Propagation.MANDATORY)
	private Map<String, UserAppAccess> createuserAppAccessMapFromUserObject(UserInfo user) {
		Map<String, UserAppAccess> retMap = new HashMap<String, UserAppAccess>();
		Set<UserAppAccess> userAppAccessList = user.getUserAppAccessList();
		for (UserAppAccess userAppAccess : userAppAccessList)
			retMap.put(userAppAccess.getAppName(), userAppAccess);

		return retMap;
	}
	@Transactional(propagation = Propagation.MANDATORY)
	private void createAndAttachTeamAllocations(Collection<Team> teams, UserInfo user) {
		logger.info("Team allocation creation request arrived.");
		if (teams == null || teams.isEmpty()) {
			logger.warning("No team provided. Existing!!");
			return;
		} else {
			logger.info("Creating allocation for " + user.getUsername() + ", and teams: " + teams);
		}
		Set<UserTeamAllocation> allocations = new HashSet<UserTeamAllocation>();
		for (Team team : teams) {
			UserTeamAllocation userTeamAllocation = new UserTeamAllocation();
			UserTeamAllocationPK pk = new UserTeamAllocationPK();
			pk.setTeam(team);
			pk.setUser(user);
			userTeamAllocation.setPk(pk);
			allocations.add(userTeamAllocation);
		}
		user.setUserTeamAllocations(allocations);
	}

	@Transactional(propagation = Propagation.MANDATORY)
	private void updateAndSaveTeamAllocations(Collection<Team> teams, UserInfo user) {
		logger.info("Team allocation creation request arrived.");
		if (teams == null || teams.isEmpty()) {
			logger.warning("No team provided. Existing!!");
			return;
		}
		logger.info("Deleted Existing teams.");
		Collection<UserTeamAllocation> userTeamAllocations = findTeamAllocationToBeDeleted(user.getUserTeamAllocations(), teams);
		if (userTeamAllocations != null && !userTeamAllocations.isEmpty()) {
			for (UserTeamAllocation userTeamAllocation : userTeamAllocations){
				user.getUserTeamAllocations().remove(userTeamAllocation);
				teamDao.delete(userTeamAllocation);
			}
		}
		Set<UserTeamAllocation> allocations = user.getUserTeamAllocations();
		if(allocations == null)
			allocations = new HashSet<UserTeamAllocation>();
		logger.info("Creating allocation for " + user.getUsername() + ", and teams: " + teams);
		for (Team team : teams) {
			UserTeamAllocation userTeamAllocation = new UserTeamAllocation();
			UserTeamAllocationPK pk = new UserTeamAllocationPK();
			pk.setTeam(team);
			pk.setUser(user);
			userTeamAllocation.setPk(pk);
			allocations.add(userTeamAllocation);
		}
		user.setUserTeamAllocations(allocations);
		logger.info("Team Allocations saved.");
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private Collection<UserTeamAllocation> findTeamAllocationToBeDeleted(Set<UserTeamAllocation> userTeamAllocations, Collection<Team> teams) {
		List<UserTeamAllocation> toBeDeleted = new ArrayList<UserTeamAllocation>();
		for(UserTeamAllocation allocation : userTeamAllocations){
			if(!teams.contains(allocation.getTeam()))
				toBeDeleted.add(allocation);
		}
		return toBeDeleted;
	}

	public void setUserInfoDao(IUserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	public void setAccessDefDao(IAccessDao accessDefDao) {
		this.accessDefDao = accessDefDao;
	}

}
