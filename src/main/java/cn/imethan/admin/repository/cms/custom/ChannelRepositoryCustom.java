package cn.imethan.admin.repository.cms.custom;

/**
 * ChannelRepositoryCustom.java
 * 自定义的Repository接口名称必须是元接口名称“ChannelRepository”+“Custom”
 * 自定义的Repository接口名称必须是元接口名称“ChannelRepository”+“Impl”
 * 
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年9月30日上午10:45:23
 */
public interface ChannelRepositoryCustom<T, ID> {

	/**
	 * Get database name.
	 * 
	 * @return
	 *
	 * @author Ethan Wong
	 * @datetime 2015年10月1日上午3:58:40
	 */
	String getDbName();

}
