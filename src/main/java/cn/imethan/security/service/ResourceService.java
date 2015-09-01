package cn.imethan.security.service;

import java.util.List;

import cn.imethan.common.dto.ReturnDto;
import cn.imethan.security.entity.Resource;


/**
 * ResourceService.java
 *
 * @author Ethan Wong
 * @time 2014年3月17日下午10:09:38
 */
public interface ResourceService {
	
	/**
	 * 保存或者修改
	 * @param entity
	 * @return
	 */
	public ReturnDto saveOrModify(Resource entity);
	
	/**
	 * 根据ID获取
	 * @param id
	 * @return
	 */
	public Resource getById(Long id);
	
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	public ReturnDto deleteById(Long id);
	
	/**
	 * 获取根节点
	 * @return
	 */
	public List<Resource> getRootResource();
	
	/**
	 * 获取资源和授权关联列表
	 * @param roleId
	 * @return
	 */
	public List<Resource> getResourcePermissionForRoleInput(Long roleId);
	
	
}


