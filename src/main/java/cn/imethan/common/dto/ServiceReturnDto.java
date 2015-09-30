package cn.imethan.common.dto;

/**
 * ServiceReturnDto.java
 *
 * @author Ethan Wong
 * @param <S>
 * @time 2014年3月8日下午10:29:21
 */
public class ServiceReturnDto<T> {
	
	private boolean isSuccess = true;
	private T entity;
	
	public ServiceReturnDto() {
		
	}
	public ServiceReturnDto(boolean isSuccess, T entity) {
		super();
		this.isSuccess = isSuccess;
		this.entity = entity;
	}
	
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public T getEntity() {
		return entity;
	}
	public void setEntity(T entity) {
		this.entity = entity;
	}

}