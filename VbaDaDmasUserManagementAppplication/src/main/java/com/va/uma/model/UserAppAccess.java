package com.va.uma.model;

import java.util.UUID;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user_app_access")
@AssociationOverrides({ @AssociationOverride(name = "pk.user", joinColumns = @JoinColumn(name = "user_id")), @AssociationOverride(name = "pk.appAccess", joinColumns = {@JoinColumn(name = "application_id"), @JoinColumn(name = "access_id")}) })
public class UserAppAccess implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7933770163144650730L;
	
	@EmbeddedId
	private UserAppAccessPK pk = new UserAppAccessPK();

	public UserAppAccessPK getPk() {
		return pk;
	}

	public void setPk(UserAppAccessPK pk) {
		this.pk = pk;
	}
	
	@Transient
	public UserInfo getUser(){
		return pk.getUser();
	}
	
	@Transient
	public AppAccess getAppAccess(){
		return pk.getAppAccess();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAppAccess other = (UserAppAccess) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}
	
	
}