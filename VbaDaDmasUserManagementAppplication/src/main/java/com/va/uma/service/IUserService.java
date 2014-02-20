package com.va.uma.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.va.uma.model.Team;
import com.va.uma.model.UserAppAccess;
import com.va.uma.model.UserInfo;
import com.va.uma.model.UserInfo.UserStatus;

public interface IUserService {
	
	void saveUser(UserInfo entity);

	void updateUser(UserInfo entity);

	void deleteUser(String userId);

	UserInfo getUserInfoByUsername(String username);

	UserInfo getUserInfoById(String id);

	List<UserInfo> listUser(int pageSize, int pageIndex);

	void saveUserAppAccess(UserAppAccess entity);

	void deleteUserAppAccess(UserAppAccess entity);

	void updateUserAppAccess(UserAppAccess entity);

	void deleteAllAppAccessByUserId(String userId);

	boolean isAppAccessUsed(String appName, String accessId);
	
	boolean isTeamUsed(String teamid);

	void sendEmailToUser(String id,String type);

	void updateUserStatusById();


	List<UserInfo> listUserByTeam(String teamId);
	
	List<UserInfo> getAllActiveOrInactiveUsers(UserStatus entity);
	
	Collection<UserAppAccess> getAllUsersInAppX(String userId);
	
	List<UserInfo> getReport(Team team,String appName,UserStatus status);

	void updateUserDetails(UserInfo user, Map<String, String> appAccessMap, Collection<Team> teams);

	void saveUserDetails(UserInfo user, Map<String, String> appAccessMap, Collection<Team> teams);

}