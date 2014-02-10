package com.va.uma.dao;

import java.util.List;

import com.va.uma.model.Team;
import com.va.uma.model.UserTeamAllocation;

public interface ITeamDao {

	void save(Team entity);

	void update(Team entity);

	void delete(Team entity);

	Team findById(String id);
	
	Team getTeamInfoByName(String team);

	List<Team> listAll();

	void save(UserTeamAllocation teamAllocation);
	
	void update(UserTeamAllocation entity);

	void delete(UserTeamAllocation entity);
}