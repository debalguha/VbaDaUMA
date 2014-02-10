package com.va.uma.dao;


public interface IDataDao {
	public void createDummyData() throws Exception;
	
	public boolean checkForDuplicatUserInfo(String username,String email  )throws Exception;
}
