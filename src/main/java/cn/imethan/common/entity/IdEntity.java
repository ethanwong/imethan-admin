package cn.imethan.common.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * IdEntity.java
 *
 * @author Ethan Wong
 * @time 2014年3月2日下午4:51:08
 */
@MappedSuperclass 
public class IdEntity implements Serializable {
	
	private static final long serialVersionUID = 8131134448502938007L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;//ID

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}


