package com.va.uma.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user_team_allocation")
@AssociationOverrides({
	@AssociationOverride(name = "pk.user", 
		joinColumns = @JoinColumn(name = "user_id")),
	@AssociationOverride(name = "pk.team", 
		joinColumns = @JoinColumn(name = "team_id")) })
public class UserTeamAllocation implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8363876881242702399L;
	/**
	 * 
	 */
	@EmbeddedId
	private UserTeamAllocationPK pk = new UserTeamAllocationPK();
	
	@Transient
	public UserInfo getUser(){
		return pk.getUser();
	}
	
	@Transient
	public Team getTeam(){
		return pk.getTeam();
	}
	
	
	@Override
	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UserTeamAllocation))
			return false;
		UserTeamAllocation other = (UserTeamAllocation) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

	public UserTeamAllocationPK getPk() {
		return pk;
	}

	public void setPk(UserTeamAllocationPK pk) {
		this.pk = pk;
	}	
}