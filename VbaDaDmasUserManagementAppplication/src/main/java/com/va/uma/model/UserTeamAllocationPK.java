package com.va.uma.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class UserTeamAllocationPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4708260427947386163L;

	@ManyToOne
	private UserInfo user;
	
	@ManyToOne
	private Team team;

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public int hashCode() {
		int result;
		result = (user != null ? user.hashCode() : 0);
		result = 17 * result + (team != null ? team.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UserTeamAllocationPK))
			return false;
		UserTeamAllocationPK other = (UserTeamAllocationPK) obj;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
}
