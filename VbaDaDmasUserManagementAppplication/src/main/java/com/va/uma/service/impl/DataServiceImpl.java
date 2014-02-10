package com.va.uma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.va.uma.dao.IDataDao;
import com.va.uma.model.AppAccess;
import com.va.uma.service.IDataService;

@Service("dataService")
@Transactional
public class DataServiceImpl implements IDataService {
	@Autowired
	private IDataDao dataDao;
	
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	@Override
	public boolean createDummyData() throws Exception {
		dataDao.createDummyData();
		return true;
	}
	public void setDataDao(IDataDao dataDao) {
		this.dataDao = dataDao;
	}

	@Override
	public boolean isNewUserHasDuplicat(String username, String email)
			throws Exception {
		return dataDao.checkForDuplicatUserInfo(username, email);
		
	}
	

}
