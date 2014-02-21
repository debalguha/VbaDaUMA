/**
 * 
 */

package com.va.uma.web.action;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.va.uma.model.Team;
import com.va.uma.model.UserAppAccess;
import com.va.uma.model.UserInfo;
import com.va.uma.model.UserInfo.UserStatus;
import com.va.uma.model.UserInfo.UserType;
import com.va.uma.model.UserTeamAllocation;
import com.va.uma.util.MD5;

@Controller
public class UserAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(UserAction.class.getName());
	
/*	@RequestMapping("/user/{userId}/details.do")
	public UserDetails userDetail(@PathVariable String userId) {
		UserInfo userInfo = userService.getUserInfoById(userId);
		Set<UserAppAccess> userAppAccessList = userInfo.getUserAppAccessList();
		Map<String, String> appAccessMap = new HashMap<String, String>();
		if(userAppAccessList!=null){
			for(UserAppAccess userAppAccess : userAppAccessList){
				String appName = userAppAccess.getAppName();
				String access = userAppAccess.getAccess().getName();
				appAccessMap.put(appName, access);
			}
		}
		List<String> teams = new ArrayList<String>();
		Set<UserTeamAllocation> userTeamAllocations = userInfo.getUserTeamAllocations();
		if(userTeamAllocations!=null){
			for(UserTeamAllocation teamAllocation : userTeamAllocations)
				teams.add(teamAllocation.getTeam().getName());
		}
		
		return new UserDetails(appAccessMap, teams);
	}*/
	
	@RequestMapping("/user/list.do")
	public String listPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("dataList", userService.listUser(0, 0));
		model.addAttribute("appList", appService.getApplicationList());
		
		try {
			long login = Calendar.getInstance().getTimeInMillis();
			UserInfo user = (UserInfo) request.getSession().getAttribute(BaseAction.ACCOUNT_SEESION_ID);
			String username=user.getUsername();
			user = userService.getUserInfoByUsername(username);
			user.setLogin(login);
			userService.updateUser(user);
	
		} catch (Exception e) {
			System.out.println("failed to updateUserLoginTime failed");
			e.printStackTrace();
		}
		return "user/list";
	}

	@RequestMapping("/user/add.do")
	public String addPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!/user/add.do");
		model.addAttribute("appList", appService.getApplicationList());
		model.addAttribute("appAccessList", appService.getAppAccessList());
		model.addAttribute("teamList", appService.getTeamList());
		return "user/add";
	}

	@RequestMapping("/user/edit.do")
	public String editPage(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(value = "id") String userId) {
		UserInfo userInfo = userService.getUserInfoById(userId);
		model.addAttribute("appList", appService.getApplicationList());
		model.addAttribute("appAccessList", appService.getAppAccessList());
		model.addAttribute("teamList", appService.getTeamList());
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("partOfTeams", getAllocatedTeamIds(userInfo));
		return "user/edit";
	}

	private String getAllocatedTeamIds(UserInfo userInfo) {
		Set<UserTeamAllocation> userTeamAllocations = userInfo.getUserTeamAllocations();
		StringBuilder builder = new StringBuilder();
		int counter=0;
		for(UserTeamAllocation userTeamAllocation : userTeamAllocations){
			builder.append(userTeamAllocation.getTeam().getId());
			counter++;
			if(counter<userTeamAllocations.size())
				builder.append(",");
		}
		return builder.toString();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/user/save.do")
	public void saveUser(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "username") String username, @RequestParam(value = "password") String password,
			@RequestParam(value = "status") String status, @RequestParam(value = "type") String type,
			@RequestParam(value = "teamId[]", required = false, defaultValue = "") String[] teamIds,
			@RequestParam(value = "phone", required = false, defaultValue = "") String phone,
			@RequestParam(value = "firstName", required = false, defaultValue = "") String firstName,
			@RequestParam(value = "lastName", required = false, defaultValue = "") String lastName,
			@RequestParam(value = "approverFullname", required = false, defaultValue = "") String approverFullname,
			@RequestParam(value = "approverEmail", required = false, defaultValue = "") String approverEmail,
			@RequestParam(value = "approverPhone", required = false, defaultValue = "") String approverPhone,
			@RequestParam(value = "requestDetail", required = false, defaultValue = "") String requestDetail) {
		UserInfo user = new UserInfo();
		Map<String, String> requestMap = new HashMap<String, String>(request.getParameterMap());
		requestMap.remove("teamId");
		requestMap.remove("type");
		requestMap.remove("status");
		Map<String, String> appAccessMap = getUserAppAccess(request, requestMap);
		try {
			BeanUtils.populate(user, requestMap);
		} catch (Exception e) {
			responseJson4Fail(response);
		}
		user.setStatus(UserStatus.valueOf(status));
		user.setType(UserType.valueOf(type));
		List<Team> teams = new ArrayList<Team>(teamIds.length);
		for(String teamId : teamIds){
			Team team = appService.getTeam(teamId);
			if(team!=null)
				teams.add(team);
		}
		userService.saveUserDetails(user, appAccessMap, teams);
		responseJson4Success(response);
	}


	private Map<String, String> getUserAppAccess(HttpServletRequest request, Map<String, String> copyMap) {
		Map<String, String> appAccessMap = new HashMap<String, String>();
		Set<String> keySet = copyMap.keySet();
		Set<String> appSet = new HashSet<String>();
		Iterator<String> iterator = keySet.iterator();
		String appPrefix = "app:";
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			if (key.startsWith(appPrefix)) {
				if (!request.getParameter(key).equals("0")) {
					appAccessMap.put(key.substring(appPrefix.length()), request.getParameter(key));
				}
				appSet.add(key.substring(appPrefix.length()));
			}
		}
		for (String appName : appSet) {
			copyMap.remove(appPrefix + appName);
		}
		return appAccessMap;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/user/update.do")
	public void updateUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userId") String userId, 
			@RequestParam(value = "status") String status,
			@RequestParam(value = "type") String type, 
			@RequestParam(value = "phone", required = false, defaultValue = "") String phone,
			@RequestParam(value = "firstName", required = false, defaultValue = "") String firstName,
			@RequestParam(value = "lastName", required = false, defaultValue = "") String lastName,
			@RequestParam(value = "approverFullname", required = false, defaultValue = "") String approverFullname,
			@RequestParam(value = "approverEmail", required = false, defaultValue = "") String approverEmail,
			@RequestParam(value = "approverPhone", required = false, defaultValue = "") String approverPhone,
			@RequestParam(value = "requestDetail", required = false, defaultValue = "") String requestDetail
		
			)  {
		System.out.println(request.getParameterValues("teamId[]"));
		System.out.println(request.getParameter("teamId[]"));
		System.out.println(new ArrayList<String>(request.getParameterMap().keySet()));
		System.out.println("####################");
		UserInfo user = userService.getUserInfoById(userId);
		String []teamIds = request.getParameterValues("teamId[]");
		Map<String, String> requestMap = new HashMap<String, String>(request.getParameterMap());
		requestMap.remove("teamId");
		requestMap.remove("type");
		requestMap.remove("status");
		Map<String, String> appAccessMap = getUserAppAccess(request, requestMap);
		try {
			BeanUtils.populate(user, requestMap);
		} catch (Exception e) {
			responseJson4Fail(response);
		}
		user.setStatus(UserStatus.valueOf(status));
		user.setType(UserType.valueOf(type));
		List<Team> teams = new ArrayList<Team>();
		if(teamIds!=null){
			logger.info("Going to fetch teams with id: "+Arrays.asList(teamIds));
			for(String teamId : teamIds){
				Team team = appService.getTeam(teamId);
				if(team!=null)
					teams.add(team);
			}
			logger.info("Teams:: "+teams);
		}
		userService.updateUserDetails(user, appAccessMap, teams);
		responseJson4Success(response);
	}


	@RequestMapping("/user/change-password.do")
	public void changePassword(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "userId") String userId, @RequestParam(value = "password") String password) {
		UserInfo user = userService.getUserInfoById(userId);
		user.setPassword(MD5.md5(password));
		userService.updateUser(user);
		responseJson4Success(response);
	}

	@RequestMapping("/user/delete.do")
	public void deleteUser(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "userId") String userId) {
		userService.deleteUser(userId);
		responseJson4Success(response);
	}
	
	public static class UserDetails{
		private final Map<String, String> applicationAccess;
		private final List<String> teams;
		public UserDetails(Map<String, String> applicationAccess,
				List<String> teams) {
			super();
			this.applicationAccess = applicationAccess;
			this.teams = teams;
		}
		public Map<String, String> getApplicationAccess() {
			return applicationAccess;
		}
		public List<String> getTeams() {
			return teams;
		}
		
	}
}
