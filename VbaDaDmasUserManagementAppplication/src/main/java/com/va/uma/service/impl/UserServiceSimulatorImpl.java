package com.va.uma.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.va.uma.model.Team;
import com.va.uma.model.UserAppAccess;
import com.va.uma.model.UserInfo;
import com.va.uma.model.UserInfo.UserStatus;
import com.va.uma.service.IUserService;
import com.va.uma.util.SimulatorData;

@Service("userServiceSimulator")
@Transactional
public class UserServiceSimulatorImpl implements IUserService {

	private static Logger logger = LoggerFactory.getLogger(UserServiceSimulatorImpl.class);
	@Override
	public void saveUser(UserInfo entity) {
		SimulatorData.userList.add(entity);
	}

	@Override
	public void updateUser(UserInfo entity) {
		for (Iterator<UserInfo> iterator = SimulatorData.userList.iterator(); iterator.hasNext();) {
			UserInfo user = (UserInfo) iterator.next();
			if (user.getId().equals(entity.getId())) {
				try {
					BeanUtils.copyProperties(user, entity);
				} catch (Exception e) {
					logger.error("", e);
					
				}
			}
		}
	}
	@Override
	public void deleteUser(String userId) {
		List<UserInfo> newList = new ArrayList<UserInfo>();
		for (Iterator<UserInfo> iterator = SimulatorData.userList.iterator(); iterator.hasNext();) {
			UserInfo user = (UserInfo) iterator.next();
			if (!user.getId().equals(userId)) {
				newList.add(user);
			}
		}
		SimulatorData.userList = newList;
	}
	@Override
	public UserInfo getUserInfoByUsername(String username) {
		List<UserAppAccess> list = new ArrayList<UserAppAccess>();
		for (Iterator<UserInfo> iterator = SimulatorData.userList.iterator(); iterator.hasNext();) {
			UserInfo user = (UserInfo) iterator.next();
			for (UserAppAccess uaa : SimulatorData.userAppAccessList) {
				if (uaa.getUserInfo().getId().equals(user.getId())) {
					list.add(uaa);
				}
			}
			user.setUserAppAccessList(list);
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}
	@Override
	public UserInfo getUserInfoById(String id) {
		List<UserAppAccess> list = new ArrayList<UserAppAccess>();
		for (Iterator<UserInfo> iterator = SimulatorData.userList.iterator(); iterator.hasNext();) {
			UserInfo user = (UserInfo) iterator.next();
			for (UserAppAccess uaa : SimulatorData.userAppAccessList) {
				if (uaa.getUserInfo().getId().equals(user.getId())) {
					list.add(uaa);
				}
			}
			user.setUserAppAccessList(list);
			if (user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}
	@Override
	public List<UserInfo> listUser(int pageSize, int pageIndex) {
		for (UserInfo userInfo : SimulatorData.userList) {
			List<UserAppAccess> list = new ArrayList<UserAppAccess>();
			for (UserAppAccess uaa : SimulatorData.userAppAccessList) {
				if (uaa.getUserInfo().getId().equals(userInfo.getId())) {
					list.add(uaa);
				}
			}
			userInfo.setUserAppAccessList(list);
		}
		return SimulatorData.userList;
	}
	@Override
	public void saveUserAppAccess(UserAppAccess entity) {
		SimulatorData.userAppAccessList.add(entity);
	}
	@Override
	public void deleteUserAppAccess(UserAppAccess entity) {
		List<UserAppAccess> newList = new ArrayList<UserAppAccess>();
		for (Iterator<UserAppAccess> iterator = SimulatorData.userAppAccessList.iterator(); iterator.hasNext();) {
			UserAppAccess uaa = (UserAppAccess) iterator.next();
			if (!uaa.getId().equals(entity.getId())) {
				newList.add(uaa);
			}
		}
		SimulatorData.userAppAccessList = newList;
	}
	@Override
	public void updateUserAppAccess(UserAppAccess entity) {
		for (Iterator<UserAppAccess> iterator = SimulatorData.userAppAccessList.iterator(); iterator.hasNext();) {
			UserAppAccess uaa = (UserAppAccess) iterator.next();
			if (uaa.getId().equals(entity.getId())) {
				try {
					BeanUtils.copyProperties(uaa, entity);
				} catch (Exception e) {
					logger.error("", e);
				}
			}
		}
	}
	@Override
	public void deleteAllAppAccessByUserId(String userId) {
		List<UserAppAccess> newList = new ArrayList<UserAppAccess>();
		for (Iterator<UserAppAccess> iterator = SimulatorData.userAppAccessList.iterator(); iterator.hasNext();) {
			UserAppAccess uaa = (UserAppAccess) iterator.next();
			if (!uaa.getUserInfo().getId().equals(userId)) {
				newList.add(uaa);
			}
		}
		SimulatorData.userAppAccessList = newList;
	}
	@Override
	public boolean isAppAccessUsed(String appName, String accessId) {
		for (Iterator<UserAppAccess> iterator = SimulatorData.userAppAccessList.iterator(); iterator.hasNext();) {
			UserAppAccess uaa = (UserAppAccess) iterator.next();
			if (uaa.getAppName().equals(appName) && uaa.getAccess().getId().equals(accessId)) {
				return true;
			}
		}
		return false;
	}



	@Override
	public boolean isTeamUsed(String teamid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateUserStatusById() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendEmailToUser(String id, String type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserInfo> listUserByTeam(String team) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<UserInfo> getAllActiveOrInactiveUsers(UserStatus active) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserAppAccess> getAllUsersInAppX(String userId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<UserInfo> getReport(Team teamId, String appName, UserStatus status) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void updateUserDetails(UserInfo user,
			Map<String, String> appAccessMap, Collection<Team> teams) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveUserDetails(UserInfo user,
			Map<String, String> appAccessMap, Collection<Team> teams) {
		// TODO Auto-generated method stub
		
	}

}