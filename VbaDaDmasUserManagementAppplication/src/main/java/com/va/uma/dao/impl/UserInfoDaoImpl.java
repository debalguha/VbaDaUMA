package com.va.uma.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.va.uma.dao.BaseDao;
import com.va.uma.dao.IUserInfoDao;
import com.va.uma.model.Team;
import com.va.uma.model.UserAppAccess;
import com.va.uma.model.UserInfo;
import com.va.uma.model.UserInfo.UserStatus;

@Repository("userInfoDao")
public class UserInfoDaoImpl extends BaseDao implements IUserInfoDao {
	@Override
	public void save(UserInfo entity) {
		super.save(entity);
	}
	@Override
	public void update(UserInfo entity) {
		super.update(entity);
	}
	@Override
	public void delete(UserInfo entity) {
		super.delete(entity);
	}
	@Override
	public UserInfo findById(String id) {
		return (UserInfo) super.findById(UserInfo.class, id);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<UserAppAccess> listUserAppAccess(String userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from UserAppAccess where userInfo.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, userId);
		return query.list();
	}
	@Override
	public boolean isAppAccessUsed(String appName, String accessId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from UserAppAccess where appName = ? and access.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, appName);
		query.setParameter(1, accessId);
		List<?> list = query.list();
		return !CollectionUtils.isEmpty(list);
	}

	@Override
	public boolean isTeamUsed(String teamid) {
		Session session= sessionFactory.getCurrentSession();
		String hql = "from UserInfo where team.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, teamid);
		List<?> list = query.list();
		return !CollectionUtils.isEmpty(list);
	}
	@Override
	public UserInfo findByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from UserInfo where username=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, username);
		return (UserInfo) query.uniqueResult();
	}
	@Override
	public UserInfo findByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from UserInfo where email=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, email);
		return (UserInfo) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> listAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from UserInfo";
		Query query = session.createQuery(hql);
		return query.list();
	}
	@Override
	public void saveUserAppAccess(UserAppAccess entity) {
		super.save(entity);
	}
	@Override
	public void deleteUserAppAccess(UserAppAccess entity) {
		super.delete(entity);
	}
	@Override
	public void updateUserAppAccess(UserAppAccess entity) {
		super.update(entity);
	}
	@Override
	public void deleteAllAppAccessByUserId(String userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "delete from UserAppAccess where userInfo.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, userId);
		query.executeUpdate();
	}
	@SuppressWarnings("unchecked")
	@Override

		public List<UserInfo> listUserByTeam(String teamId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from UserInfo where team_id  = ?";
		Query query = session.createQuery(hql);
		
		query.setParameter(0, teamId);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> getAllActiveUsers(UserStatus entity) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from UserInfo where status  = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, entity);
		System.out.println(query.list());
		return query.list();
	}
	@Override
	public List<UserAppAccess> getAllUsersInAppX(String userId) {

		Session session = sessionFactory.getCurrentSession();
		String hql = "from UserAppAccess where userInfo  = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, userId);
		System.out.println(query.list());
		return query.list();
	}
	@Override
	public List<UserInfo> getReport(Team team,String appName,UserStatus status) {
		System.out.println("teamId :"+ (team==null?"null":team.getId())+", appName :"+ appName+", status :"+status);
		Session session = sessionFactory.getCurrentSession();
		
		StringBuilder builder = new StringBuilder("SELECT u From  UserInfo u, UserAppAccess a FETCH ALL PROPERTIES WHERE u.id = a.userInfo.id");
		if(team!=null)
			builder.append(" ").append("and u.team = :team");
		if(StringUtils.hasText(appName))
			builder.append(" ").append("and a.appName = :appName");
		builder.append(" ").append("and u.status = :status");
		
		 //String hql = "SELECT u.firstName,u.username,u.status,u.lastName,u.phone,u.team  From  UserInfo u,AppAccess a FETCH ALL PROPERTIES WHERE u.team.id = ? AND a.appName = :appName AND u.user.id = a.user.id ";
	     
		Query query = session.createQuery(builder.toString());
		if(team!=null)
			query.setParameter("team", team);
		if(StringUtils.hasText(appName))
			query.setParameter("appName", appName);		
		query.setParameter("status", status);
//		query.setParameter("userId", "c90f4cff-768b-4009-85f7-862e4131a27c");
		System.out.println(query.list());
		query.setResultTransformer(Criteria.PROJECTION);
//		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.list();


		
	}


}