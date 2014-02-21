package com.va.uma.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.va.uma.dao.BaseDao;
import com.va.uma.dao.IApplicationDao;
import com.va.uma.model.Access;
import com.va.uma.model.AppAccess;
import com.va.uma.model.AppAccessPK;
import com.va.uma.model.Application;

@Repository("applicationDao")
public class ApplicationDaoImpl extends BaseDao implements IApplicationDao {
	@Override
	public void saveAppAccess(AppAccess entity) {
		super.save(entity);
	}
	@Override
	public void updateAppAccess(AppAccess entity) {
		super.update(entity);
	}
	@Override
	public void delete(AppAccess entity) {
		super.delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AppAccess> listAllAppAccess() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from AppAccess";
		Query query = session.createQuery(hql);
		return query.list();
	}
	@Override
	public AppAccess findById(String id) {
		return (AppAccess) super.findById(AppAccess.class, id);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Application> listAllApp() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Application";
		Query query = session.createQuery(hql);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<AppAccess> findAppAccessForApp(Application app) {
		String query = "select aa from AppAccess aa where aa.application = :application";
		return (List<AppAccess>)sessionFactory.getCurrentSession().createQuery(query).list();
	}
	@Override
	public Application findApplication(String appName) {
		return (Application)findById(Application.class, appName);
	}
	@Override
	public AppAccess findAppAccessForAppAndAccess(String app, String accessId) {
		AppAccessPK pk = new AppAccessPK();
		pk.setAccess((Access)findById(Access.class, accessId));
		pk.setApplication(findApplication(app));
		return (AppAccess)findById(AppAccess.class, pk);
	}

}