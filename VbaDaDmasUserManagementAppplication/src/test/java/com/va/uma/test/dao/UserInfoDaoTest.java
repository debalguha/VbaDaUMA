package com.va.uma.test.dao;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.va.uma.model.Team;
import com.va.uma.model.UserAppAccess;
import com.va.uma.model.UserInfo;
import com.va.uma.model.UserInfo.UserStatus;
import com.va.uma.model.UserInfo.UserType;
import com.va.uma.util.MD5;

public class UserInfoDaoTest extends BaseDaoTest {
	//private String name = "test";
	@Ignore
	@Test
	public void save() {
		UserInfo info = new UserInfo();
		info.setUsername("reza");
		info.setPassword(MD5.md5("123456"));
		info.setStatus(UserStatus.active);
		info.setType(UserType.creator);
		userInfoDao.save(info);
	}
	//@Ignore
	@Test
	public void findByUsername() {
		UserInfo obj = userInfoDao.findByUsername("reza");
		Assert.assertEquals("reza", obj.getUsername());
		System.out.println(obj);
	}
	@Ignore
	@Test
	public void listAll() {
		List<UserInfo> list = userInfoDao.listAll();
		Assert.assertEquals(1, list.size());
		System.out.println(list);
	}
	//@Ignore
	@Test
	public void update() {
		String username = "reza";
		UserInfo obj = userInfoDao.findByUsername(username);
		obj.setStatus(UserStatus.inactive);
		userInfoDao.update(obj);
		UserInfo q = userInfoDao.findByUsername(username);
		Assert.assertEquals(UserStatus.inactive, q.getStatus());
		System.out.println(q);
	}
	@Ignore
	@Test
	public void delete() {
		UserInfo info = new UserInfo();
		info.setId("a2cee740-1041-480d-b4e1-e8bdac3683e9");
		userInfoDao.delete(info);
	}
	@Ignore
	@Test
	public void saveUserAppAccess() {
		UserAppAccess entity = new UserAppAccess();
		List<UserInfo> userList = userInfoDao.listAll();
		entity.setUserInfo(userList.get(0));
		entity.setAppName(applicationDao.listAllApp().get(0).getName());
		entity.setAccess(accessDefDao.listAll().get(0));
		userInfoDao.saveUserAppAccess(entity);
	}
	@Ignore
	@Test
	public void listUserAppAccess() {
		Set<UserAppAccess> list = userInfoDao.findById("43fa0d78-8d83-4508-8b02-ac4cf171dbdd").getUserAppAccessList();
		System.out.println(list);
	}
	@Ignore
	@Test
	public void deleteAllAppAccessByUserId() {
		userInfoDao.deleteAllAppAccessByUserId("43fa0d78-8d83-4508-8b02-ac4cf171dbdd");
	}
	@Ignore
	@Test
	public void getReportTest(){
		List<?> report = null;
		try {
			Team team = teamDao.findById("28f66133-26c3-442b-a071-4d19d64ec0ae");
			report = userInfoDao.getReport(team, "app100", UserStatus.active);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(report);
		assertTrue(report.size()>0);
	}
}
