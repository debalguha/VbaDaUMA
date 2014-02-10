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
import com.va.uma.model.Team;
import com.va.uma.model.UserAppAccess;
import com.va.uma.model.UserInfo;
import com.va.uma.model.UserTeamAllocationPK;
import com.va.uma.model.UserInfo.UserStatus;
import com.va.uma.model.UserTeamAllocation;
import com.va.uma.service.IUserService;

@Service("userService")
@Transactional
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
	public void updateUser(UserInfo entity) {
		userInfoDao.update(entity);
	}

	@Override
	public void deleteUser(String userId) {
		userInfoDao.delete(userInfoDao.findById(userId));
	}

	@Override
	public UserInfo getUserInfoByUsername(String username) {
		UserInfo userInfo = userInfoDao.findByUsername(username);
		userInfo.setUserAppAccessList(userInfoDao.listUserAppAccess(userInfo
				.getId()));
		return userInfo;
	}

	@Override
	public UserInfo getUserInfoById(String id) {
		UserInfo userInfo = userInfoDao.findById(id);
		userInfo.setUserAppAccessList(userInfoDao.listUserAppAccess(userInfo
				.getId()));
		return userInfo;
	}

	@Override
	public List<UserInfo> listUser(int pageSize, int pageIndex) {
		List<UserInfo> list = userInfoDao.listAll();
		for (UserInfo userInfo : list) {
			userInfo.setUserAppAccessList(userInfoDao
					.listUserAppAccess(userInfo.getId()));
		}
		return list;
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
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));

			if (type == "alert") {
				message.setSubject("your account status ");
				message.setText(userInfo.getAlert());
			}
			if (type == "creator") {
				message.setSubject("your password has been changed !");
				message.setText("For security reason your password has"
						+ " been changed by Adminstrator please contact to UMAaDMIN@DNCX.COM");
			}
			if (type == "user") {
				message.setSubject("your password has been changed !");
				message.setText("your password has been Successfully changed"
						+ " if you havent change the password  please contact immidtately to UMAaDMIN@DNCX.COM");
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

					user.setAlert("alert:Your Account will be inactive by :"
							+ remainDays
							+ "  Days.please login to your acount to"
							+ " avoid your account become inactive ");
					try {

						sendEmailToUser(user.getId(), "alert");

					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("updateUserStatusById INSIDE "
								+ "diffDays>30 failed send email failed");
					}
				}
				if (diffDays > 30) {
					user.setStatus(UserStatus.inactive);
					user.setAlert("$$$$$$$$$$$$$$$$$$$the  account didnt Login for:"
							+ diffDays
							+ "  days and now is inactive. "
							+ "please contact to admin ");

					try {
						sendEmailToUser(user.getId(), "alert");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("updateUserStatusById INSIDE"
								+ " diffDays>30 failed send email failed ");
					}

				}

			}

		}
	}

	@Override
	public List<UserInfo> listUserByTeam(String teamId) {
		List<UserInfo> list = userInfoDao.listUserByTeam(teamId);
		return list;
	}

	@Override
	public List<UserInfo> getAllActiveOrInactiveUsers(UserStatus enrity) {

		List<UserInfo> list = new ArrayList<UserInfo>();
		list = userInfoDao.getAllActiveUsers(enrity);
		return list;
	}

	@Override
	public List<UserAppAccess> getAllUsersInAppX(String userId) {
		List<UserAppAccess> list = new ArrayList<UserAppAccess>();
		list = userInfoDao.getAllUsersInAppX(userId);
		return list;
	}

	@Override
	public List<UserInfo> getReport(Team teamId, String appName,
			UserStatus status) {
		List<UserInfo> list = new ArrayList<UserInfo>();
		list = userInfoDao.getReport(teamId, appName, status);
		return list;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateUserDetails(UserInfo updatedUser, Map<String, String> appAccessMap, Collection<Team> teams) {
		logger.info("User update request has arrived.");
		UserInfo user = getUserInfoById(updatedUser.getId());
		if(!user.equals(updatedUser)){
			logger.info("Core user attributes have changed. Will update UserInfo entity");
			BeanUtils.copyProperties(updatedUser, user);
			userInfoDao.update(user);
		}else{
			updatedUser = user;
			logger.warning("Core user attributes have not changed. Will not update UserInfo entity");
		}
		Collection<UserAppAccess> userAppAccesses = createUserAppAccList(appAccessMap, user);
		for(UserAppAccess userAppAccess : userAppAccesses){
			userInfoDao.updateUserAppAccess(userAppAccess);
		}
		updateAndSaveTeamAllocations(teams, user);
	}

	public void setTeamDao(ITeamDao teamDao) {
		this.teamDao = teamDao;
	}

	
	private Collection<UserAppAccess> createUserAppAccList(Map<String, String> appAccessMap, UserInfo user) {
		logger.info("Creating UserAppAccess update list");
		Set<UserAppAccess> appAccesses = new HashSet<UserAppAccess>();
		Set<String> appAccessSet = appAccessMap.keySet();
		Iterator<String> appAccessIterator = appAccessSet.iterator();
		Map<String, UserAppAccess> userAppAccessMapFromUserObject = createuserAppAccessMapFromUserObject(user);
		while (appAccessIterator.hasNext()) {
			String appName = (String) appAccessIterator.next();
			String access = appAccessMap.get(appName);
			UserAppAccess userAppAccess = new UserAppAccess();
			userAppAccess.setUserInfo(user);
			userAppAccess.setAppName(appName);
			userAppAccess.setAccess(accessDefDao.findById(access));
			if(!userAppAccess.equals(userAppAccessMapFromUserObject.get(appName))){
				logger.info("Found one App Access to be updated old["+userAppAccessMapFromUserObject+"], new[Access: "+userAppAccess.getAccess()+"]");
				UserAppAccess userAppAccessToAdd = userAppAccessMapFromUserObject.get(appName);
				userAppAccessToAdd.setAccess(userAppAccess.getAccess());
				appAccesses.add(userAppAccessToAdd);
			}
		}
		logger.info("Total "+appAccesses.size()+" user app accesses to update.");
		return appAccesses;
	}
	
	private Map<String, UserAppAccess> createuserAppAccessMapFromUserObject(UserInfo user) {
		Map<String, UserAppAccess> retMap = new HashMap<String, UserAppAccess>();
		List<UserAppAccess> userAppAccessList = user.getUserAppAccessList();
		for(UserAppAccess userAppAccess : userAppAccessList)
			retMap.put(userAppAccess.getAppName(), userAppAccess);
		
		return retMap;
	}	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveUserDetails(UserInfo user, Map<String, String> appAccessMap, Collection<Team> teams) {
		saveUser(user);
		for(Map.Entry<String, String> entry : appAccessMap.entrySet()){
			String appName = entry.getKey();
			String accessId = entry.getValue();
			UserAppAccess userAppAccess = new UserAppAccess();
			userAppAccess.setUserInfo(user);
			userAppAccess.setAppName(appName);
			userAppAccess.setAccess(accessDefDao.findById(accessId));
			userInfoDao.saveUserAppAccess(userAppAccess);
		}
		createAndSaveTeamAllocations(teams, user);
	}
	
	@Transactional(propagation=Propagation.MANDATORY)
	private void createAndSaveTeamAllocations(Collection<Team> teams, UserInfo user){
		logger.info("Team allocation creation request arrived.");
		if(teams==null || teams.isEmpty()){
			logger.warning("No team provided. Existing!!");
			return;
		}else{
			logger.info("Creating allocation for "+user.getUsername()+", and teams: "+teams);
		}
		for(Team team : teams){
			UserTeamAllocation userTeamAllocation = new UserTeamAllocation();
			UserTeamAllocationPK pk = new UserTeamAllocationPK();
			pk.setTeam(team);
			pk.setUser(user);
			userTeamAllocation.setPk(pk);
			logger.info("Saving team: "+team);
			teamDao.save(userTeamAllocation);
		}
		logger.info("Team Allocations saved.");
	}
	
	@Transactional(propagation=Propagation.MANDATORY)
	private void updateAndSaveTeamAllocations(Collection<Team> teams, UserInfo user){
		logger.info("Team allocation creation request arrived.");
		if(teams==null || teams.isEmpty()){
			logger.warning("No team provided. Existing!!");
			return;
		}
		logger.info("Deleted Existing teams.");
		Set<UserTeamAllocation> userTeamAllocations = user.getUserTeamAllocations();
		if(userTeamAllocations!=null && !userTeamAllocations.isEmpty()){
			for(UserTeamAllocation userTeamAllocation : userTeamAllocations)
				teamDao.delete(userTeamAllocation);
		}
		logger.info("Creating allocation for "+user.getUsername()+", and teams: "+teams);
		for(Team team : teams){
			UserTeamAllocation userTeamAllocation = new UserTeamAllocation();
			UserTeamAllocationPK pk = new UserTeamAllocationPK();
			pk.setTeam(team);
			pk.setUser(user);
			userTeamAllocation.setPk(pk);
			logger.info("Saving team: "+team);
			teamDao.save(userTeamAllocation);
		}
		logger.info("Team Allocations saved.");
	}	
	
	public void setUserInfoDao(IUserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	public void setAccessDefDao(IAccessDao accessDefDao) {
		this.accessDefDao = accessDefDao;
	}

}
