package com.va.uma.test.service;

import org.junit.Test;

import com.va.uma.model.UserInfo;

import junit.framework.TestCase;

public class UserServiceImplTest extends BaseServiceTest {
	@Test
	public void testUpdateUserDetails(){
		UserInfo user = userService.getUserInfoById("c90f4cff-768b-4009-85f7-862e4131a27c");
		
	}
}
