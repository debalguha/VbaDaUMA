package com.va.uma.model;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;



@Entity
@Table(name = "user_info", uniqueConstraints = { @UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "email") })
public class UserInfo implements java.io.Serializable {

	public enum UserStatus {
		active, inactive
	}

	public enum UserType {
		user, creator
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2650114334774359089L;
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 100)
	private String id;
	@Column(name = "username", unique = true, nullable = false, length = 50)
	private String username;
	@Column(name = "password", nullable = false, length = 80)
	private String password;
	@Column(name = "status", nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	@Column(name = "type", nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	private UserType type;
	@Column(name = "phone", nullable = true, length = 30)
	private String phone;
	@Column(name = "email", nullable = true, length = 50)
	private String email;
	@Column(name = "first_name", nullable = true, length = 50)
	private String firstName;
	@Column(name = "middle_name", nullable = true, length = 50)
	private String middleName;
	@Column(name = "last_name", nullable = true, length = 50)
	private String lastName;
	@Column(name = "login", nullable = true, length = 100)
	private long login;
	@Column(name = "alert", nullable= true, length=500)
	private String alert;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.user")
	@Cascade({CascadeType.DELETE, CascadeType.SAVE_UPDATE})
	private Set<UserTeamAllocation> userTeamAllocations;

	@Column(name = "create_date", nullable = false)
	private Date createDate;
	@Column(name = "approver_fullname", nullable = true, length = 100)
	private String approverFullname;
	@Column(name = "approver_email", nullable = true, length = 50)
	private String approverEmail;
	@Column(name = "approver_phone", nullable = true, length = 30)
	private String approverPhone;
	@Column(name = "request_detail", nullable = true, length = 1000)
	private String requestDetail;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "userInfo")
	@Cascade({CascadeType.DELETE, CascadeType.SAVE_UPDATE})
	private Set<UserAppAccess> userAppAccessList;

	public UserInfo() {
		this.id = UUID.randomUUID().toString();
		this.createDate = new Date();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getApproverFullname() {
		return approverFullname;
	}

	public void setApproverFullname(String approverFullname) {
		this.approverFullname = approverFullname;
	}

	public String getApproverEmail() {
		return approverEmail;
	}

	public void setApproverEmail(String approverEmail) {
		this.approverEmail = approverEmail;
	}

	public String getApproverPhone() {
		return approverPhone;
	}

	public void setApproverPhone(String approverPhone) {
		this.approverPhone = approverPhone;
	}

	public String getRequestDetail() {
		return requestDetail;
	}

	public void setRequestDetail(String requestDetail) {
		this.requestDetail = requestDetail;
	}

	public String getPhone() {
		return phone;
	}

	

	public long getLogin() {
		return login;
	}

	public void setLogin(long login) {
		this.login = login;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public Set<UserAppAccess> getUserAppAccessList() {
		return userAppAccessList;
	}

	public void setUserAppAccessList(Set<UserAppAccess> userAppAccessList) {
		this.userAppAccessList = userAppAccessList;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public Set<UserTeamAllocation> getUserTeamAllocations() {
		return userTeamAllocations;
	}

	public void setUserTeamAllocations(Set<UserTeamAllocation> userTeamAllocations) {
		this.userTeamAllocations = userTeamAllocations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((approverEmail == null) ? 0 : approverEmail.hashCode());
		result = prime
				* result
				+ ((approverFullname == null) ? 0 : approverFullname.hashCode());
		result = prime * result
				+ ((approverPhone == null) ? 0 : approverPhone.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + (int) (login ^ (login >>> 32));
		result = prime * result
				+ ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result
				+ ((requestDetail == null) ? 0 : requestDetail.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		UserInfo other = (UserInfo) obj;
		if (approverEmail == null) {
			if (other.approverEmail != null)
				return false;
		} else if (!approverEmail.equals(other.approverEmail))
			return false;
		if (approverFullname == null) {
			if (other.approverFullname != null)
				return false;
		} else if (!approverFullname.equals(other.approverFullname))
			return false;
		if (approverPhone == null) {
			if (other.approverPhone != null)
				return false;
		} else if (!approverPhone.equals(other.approverPhone))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (login != other.login)
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (requestDetail == null) {
			if (other.requestDetail != null)
				return false;
		} else if (!requestDetail.equals(other.requestDetail))
			return false;
		if (status != other.status)
			return false;
		if (type != other.type)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}