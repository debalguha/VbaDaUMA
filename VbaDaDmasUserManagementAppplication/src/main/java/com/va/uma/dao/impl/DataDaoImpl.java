package com.va.uma.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.va.uma.dao.BaseDao;
import com.va.uma.dao.IDataDao;

@Repository("dataDao")
public class DataDaoImpl extends BaseDao implements IDataDao {
	@Override
	public void createDummyData() throws Exception {
		
	}

	@Override
	public boolean checkForDuplicatUserInfo(String username,String email ) throws Exception {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "from UserInfo where username = ? or email = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, username);
		query.setParameter(1, email);
		List<?> list = query.list();
		System.out.println("DataDaoImpl list is :"+list);
		return !CollectionUtils.isEmpty(list);	
	}
	
}
