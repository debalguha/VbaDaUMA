package com.va.uma.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class UserAppAccessPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8178006494099438042L;
	
	@ManyToOne
	private UserInfo user;
	
	@ManyToOne
	private AppAccess appAccess;

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public AppAccess getAppAccess() {
		return appAccess;
	}

	public void setAppAccess(AppAccess appAccess) {
		this.appAccess = appAccess;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appAccess == null) ? 0 : appAccess.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		UserAppAccessPK other = (UserAppAccessPK) obj;
		if (appAccess == null) {
			if (other.appAccess != null)
				return false;
		} else if (!appAccess.equals(other.appAccess))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
}
