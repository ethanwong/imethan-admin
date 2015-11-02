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
    public int compare(Menu arg0, Menu arg1) {
        if (arg0.getId() > arg1.getId()) {
            return 1;
        } else if (arg0.getId() == arg1.getId()) {
            return 0;
        } else {
            return -1;
        }
    }

}
