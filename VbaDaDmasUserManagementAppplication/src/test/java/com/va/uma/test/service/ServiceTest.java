package com.va.uma.test.service;

import org.junit.Ignore;
import org.junit.Test;

import com.va.uma.model.Team;
import com.va.uma.model.UserInfo;

public class ServiceTest extends BaseServiceTest {

	@Test
	public void userGetUser() {
		UserInfo obj = userService.getUserInfoByUsername("reza");
		System.out.println(obj);
	}
	@Ignore
	@Test
	public void saveTeam() {
		appService.saveTeam(new Team("aaa", "002"));
	}

}
