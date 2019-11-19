package com.franciscoguemes.warehouseapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.ToString;


@ToString
@SuppressWarnings("serial")
@MappedSuperclass
public class BaseEntity implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	@Version
	protected Integer version;

	public Integer getVersion() {
		return version;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (getClass() != obj.getClass()) {
			return false;
		}else {
			BaseEntity other = (BaseEntity) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			}
			else if (!id.equals(other.id))
				return false;
			if (version == null) {
				if (other.version != null)
					return false;
			}
			else if (!version.equals(other.version))
				return false;
			return true;
		}
	}

}
