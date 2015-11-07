package cn.imethan.security.utils.comparator;

import java.util.Comparator;

import cn.imethan.security.entity.Menu;

/**
 * MenuComparator.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年11月2日下午1:55:32
 */
public class MenuComparator implements Comparator<Menu> {
	
	@Override
	public int compare(Menu o1, Menu o2) {
		if(o1.getOrderNo()>o2.getOrderNo()){
			return 1;
		}else if(o1.getOrderNo()<o2.getOrderNo()){
			return -1;
		}else{
			return o1.getId().compareTo(o2.getId());
		}
	}

}
