package cn.imethan.other;

import java.util.UUID;

import org.junit.Test;

/**
 * UniqueIdTest.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年10月8日下午2:54:19
 */
public class UniqueIdTest {
	
	/**
	 * Java 产生 GUID，利用导入类 java.util.UUID，生成的GUID为一串32位字符组成的128位数据，可以做到全球唯一
	 *
	 * @author Ethan Wong
	 * @datetime 2015年10月8日下午3:00:03
	 */
	@Test
	public void guid() {
		// 产生 5 个 GUID
		for (int i = 0; i < 5; i++) {
			// 创建 GUID 对象
			UUID uuid = UUID.randomUUID();
			// 得到对象产生的ID
			String a = uuid.toString();
			// 转换为大写
			a = a.toUpperCase();
			// 替换 -
			// a = a.replaceAll("-", "");
			System.out.println(a);
		}
	}

}
