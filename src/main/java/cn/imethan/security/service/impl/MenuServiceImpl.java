package cn.imethan.security.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.imethan.common.dto.ReturnDto;
import cn.imethan.common.hibernate.SearchFilter;
import cn.imethan.security.dao.MenuDao;
import cn.imethan.security.entity.Menu;
import cn.imethan.security.service.MenuService;

/**
 * MenuServiceImpl.java
 *
 * @author Ethan Wong
 * @time 2015年9月1日下午2:27:08
 */
@Service
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {
	
	private Logger logger = Logger.getLogger(MenuServiceImpl.class); 
	
	@Autowired
	private MenuDao resourceDao;
	private ReturnDto returnDto = new ReturnDto(true,"操作成功");
	
	@Transactional(readOnly = false)
	@Override
	public ReturnDto saveOrModify(Menu entity) {
		try {
			resourceDao.save(entity);
			returnDto.setEntity(entity);
		} catch (Exception e) {
			e.printStackTrace();
			returnDto = new ReturnDto(false,"保存失败");
			logger.error("保存失败", e.fillInStackTrace());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return returnDto;
	}

	@Override
	public Menu getById(Long id) {
		return resourceDao.getById(id);
	}
	
	@Transactional(readOnly = false)
	@Override
	public ReturnDto deleteById(Long id) {
		try {
			resourceDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			returnDto = new ReturnDto(false,"操作失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("删除失败", e.fillInStackTrace());
		}
		return returnDto;
	}

	@Override
	public List<Menu> getRootMenu() {
		SearchFilter searchFilter = new SearchFilter("EQB_isRoot","true");
		return resourceDao.getByFilter(searchFilter, false);
	}

	@Override
	public List<Menu> getMenuPermissionForRoleInput(Long roleId) {
		//获取选中的资源和授权信息
//		Role role = roleRepository.findOne(roleId);
//		
//		Set<Resource> checkResourceSet = new HashSet<Resource>();
//		Set<Permission> checkPermissionSet = new HashSet<Permission>();
//		if(role != null){
//			checkResourceSet = role.getResources();
//			checkPermissionSet = role.getPermissions();
//		}
//		
//		Set<Resource> resources = resourceRepository.findByIsRoot(true);
//		for(Resource resource : resources){//遍历父级节点
//			
//			//设置父级资源节点是否选中
//			if(checkResourceSet.contains(resource)){
//				resource.setChecked(true);
//			}
//			
//			Set<Resource> childrens = resource.getChildrens();
//			for(Resource children : childrens){//遍历子级节点
//				
//				//设置子级资源节点是否选中
//				if(checkResourceSet.contains(children)){
//					children.setChecked(true);
//					resource.setChecked(true);
//				}
//				
//				Set<Permission> permissions = children.getPermissions();//子级节点的授权信息
//				if(children.getChildrens() ==null || children.getChildrens().isEmpty()){
//					Set<Resource> resourceChildrensTemp = new HashSet<Resource>();
//					
//					for(Permission permission : permissions){
//						Resource resourceChildrenTemp = new Resource();
//						resourceChildrenTemp.setId(permission.getId());
//						resourceChildrenTemp.setName(permission.getName());
//						resourceChildrenTemp.setNodeType("permission");
//						
//						//设置授权节点是否选中
//						if(checkPermissionSet.contains(permission)){
//							resourceChildrenTemp.setChecked(true);
//							children.setChecked(true);
//							resource.setChecked(true);
//						}
//						resourceChildrensTemp.add(resourceChildrenTemp);
//					}
//					children.setChildrens(resourceChildrensTemp);
//				}
//			}
//		}
//		return resources;
		return null;
	}

}


