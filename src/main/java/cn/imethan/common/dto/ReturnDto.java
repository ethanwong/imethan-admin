package cn.imethan.common.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * ReturnDto.java
 *
 * @author Ethan Wong
 * @param <S>
 * @time 2014年3月8日下午10:29:21
 */
public class ReturnDto {
	private boolean isSuccess = true;
	private String message = "Succeed";
	private Object object;
	
	public ReturnDto() {
		
	}
	public ReturnDto(boolean isSuccess) {
		if(!isSuccess){
			message = "Failure";
		}else{
			message = "Succeed";
		}
	}
	public ReturnDto(boolean isSuccess, Object object) {
		super();
		this.isSuccess = isSuccess;
		this.object = object;
	}
	
	public ReturnDto(boolean isSuccess,String message) {
		super();
		this.isSuccess = isSuccess;
		this.message = message;
	}
	
	public ReturnDto(boolean isSuccess,String message, Object object) {
		super();
		this.isSuccess = isSuccess;
		this.message = message;
		this.object = object;
	}
	
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}