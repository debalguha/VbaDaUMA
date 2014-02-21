package com.va.uma.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "app_access")
@AssociationOverrides({ @AssociationOverride(name = "pk.application", joinColumns = @JoinColumn(name = "application_id")), @AssociationOverride(name = "pk.access", joinColumns = @JoinColumn(name = "access_id")) })
public class AppAccess implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7933770163144650730L;
	
	@EmbeddedId
	private AppAccessPK pk = new AppAccessPK();

	public AppAccessPK getPk() {
		return pk;
	}

	public void setPk(AppAccessPK pk) {
		this.pk = pk;
	}
	
	@Transient
	public Application getApplication(){
		return pk.getApplication();
	}
	
	@Transient
	public Access getAccess(){
		return pk.getAccess();
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
		AppAccess other = (AppAccess) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}
	
	
}