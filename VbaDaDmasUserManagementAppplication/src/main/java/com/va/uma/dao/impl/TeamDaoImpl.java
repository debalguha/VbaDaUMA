package com.va.uma.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.va.uma.dao.BaseDao;
import com.va.uma.dao.ITeamDao;
import com.va.uma.model.Team;
import com.va.uma.model.UserTeamAllocation;

@Repository("teamDao")
public class TeamDaoImpl extends BaseDao implements ITeamDao {
	@Override
	public void save(Team entity) {
		super.save(entity);
	}
	@Override
	public void update(Team entity) {
		super.update(entity);
	}
	@Override
	public void delete(Team entity) {
		super.delete(entity);
		
	}
	@Override
	public Team findById(String id) {
		return (Team) super.findById(Team.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Team> listAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Team";
		Query query = session.createQuery(hql);
		return query.list();
	}
	

	@Override
	public  Team getTeamInfoByName(String name) {

		Session session = sessionFactory.getCurrentSession();

		String hql = "select t from Team t where name = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, name);
		return (Team)query.uniqueResult();
	}
	@Override
	public void save(UserTeamAllocation teamAllocation) {
		super.save(teamAllocation);
	}
	@Override
	public void update(UserTeamAllocation entity) {
		super.update(entity);
	}
	@Override
	public void delete(UserTeamAllocation entity) {
		super.delete(entity);
	}

}
	
