package com.va.uma.service;

public interface IDataService {
	public boolean createDummyData() throws Exception;

	public boolean isNewUserHasDuplicat(String username, String email)throws Exception;
}
