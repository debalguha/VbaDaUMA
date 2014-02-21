package com.va.uma.test.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.va.uma.model.Team;
import com.va.uma.model.UserInfo;
import com.va.uma.model.UserInfo.UserStatus;
import com.va.uma.model.UserInfo.UserType;
import com.va.uma.util.MD5;

public class UserServiceImplTest extends BaseServiceTest {
	@Ignore
	@Test
	public void testUpdateUserDetails(){
		
		UserInfo user = userService.getUserInfoByUsername("debal");
		
		user.setStatus(UserStatus.inactive);
		
		Map<String, String> appAccessMap = new HashMap<String, String>();
		appAccessMap.put("app1", "da5c0b53-6cb4-43c6-8313-ed47ff2dd085"); //changed
		appAccessMap.put("app100", "b99154c5-34bd-4707-a6d3-bd11a99d314a");
		appAccessMap.put("app2", "b99154c5-34bd-4707-a6d3-bd11a99d314a");
		appAccessMap.put("app3", "b99154c5-34bd-4707-a6d3-bd11a99d314a");
		appAccessMap.put("app4", "b99154c5-34bd-4707-a6d3-bd11a99d314a");
		
		Team teamTest = appService.getTeam("28f66133-26c3-442b-a071-4d19d64ec0ae");
		Team team1 = appService.getTeam("d86a5cb8-d6a1-49c9-86fa-dbb429bc0890");
		userService.updateUserDetails(user, appAccessMap, Arrays.asList(new Team[]{teamTest, team1}));
		
		user = userService.getUserInfoByUsername("debal");
		Assert.assertTrue(user.getStatus().equals(UserStatus.inactive));
		Assert.fail("Not implemented yet");
	}
	
	@Test
	public void testCreateUserDetails(){
		UserInfo user = new UserInfo();
		user.setEmail("debalguha@gmail.com");
		user.setFirstName("Debal");;
		user.setLastName("Guha");
		user.setLogin(RandomUtils.nextLong());
		user.setPassword(MD5.md5("123456"));
		user.setStatus(UserStatus.active);
		user.setType(UserType.user);
		user.setCreateDate(new Date());
		
		Map<String, String> appAccessMap = new HashMap<String, String>();
		appAccessMap.put("app1", "da5c0b53-6cb4-43c6-8313-ed47ff2dd085"); //changed
		appAccessMap.put("app100", "b99154c5-34bd-4707-a6d3-bd11a99d314a");
		appAccessMap.put("app2", "b99154c5-34bd-4707-a6d3-bd11a99d314a");
		appAccessMap.put("app3", "b99154c5-34bd-4707-a6d3-bd11a99d314a");
		appAccessMap.put("app4", "b99154c5-34bd-4707-a6d3-bd11a99d314a");
		
		Team teamTest = appService.getTeam("28f66133-26c3-442b-a071-4d19d64ec0ae");
		Team team1 = appService.getTeam("d86a5cb8-d6a1-49c9-86fa-dbb429bc0890");
		
		userService.saveUserDetails(user, appAccessMap, Arrays.asList(new Team[]{teamTest, team1}));
		Assert.assertNotNull(user.getId());
	}
}
