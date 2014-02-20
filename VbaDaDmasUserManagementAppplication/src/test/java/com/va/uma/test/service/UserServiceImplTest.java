package com.va.uma.test.service;

import org.junit.Test;

import com.va.uma.model.UserInfo;

import junit.framework.TestCase;

public class UserServiceImplTest extends BaseServiceTest {
	@Test
	public void testUpdateUserDetails(){
		UserInfo user = userService.getUserInfoByUsername("debal");
		
		
	}
}
