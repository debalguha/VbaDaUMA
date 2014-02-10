package com.va.uma.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_app_access")
@Embeddable
public class UserAppAccess implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7933770163144650730L;
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 80)
	private String id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private UserInfo userInfo;

	@Column(name = "app_name", nullable = false, length = 100)
	private String appName;

	@OneToOne
	@JoinColumn(name = "access_id")
	private Access access;

	public UserAppAccess() {
		this.id = UUID.randomUUID().toString();
	}

	public UserAppAccess(UserInfo userInfo, String appName, Access access) {
		this.id = UUID.randomUUID().toString();
		this.userInfo = userInfo;
		this.appName = appName;
		this.access = access;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Access getAccess() {
		return access;
	}

	public void setAccess(Access access) {
		this.access = access;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((access == null) ? 0 : access.hashCode());
		result = prime * result + ((appName == null) ? 0 : appName.hashCode());
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
		if (access == null) {
			if (other.access != null)
				return false;
		} else if (!access.equals(other.access))
			return false;
		if (appName == null) {
			if (other.appName != null)
				return false;
		} else if (!appName.equals(other.appName))
			return false;
		return true;
	}
	
	
}