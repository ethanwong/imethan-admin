package cn.imethan.other;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * BCryptPasswordEncoderTest.java
 *
 * @author Ethan Wong
 * @time 2015年9月4日下午6:13:30
 */
public class BCryptPasswordEncoderTest {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String pwd = bCryptPasswordEncoder.encode("123456");
		System.out.println("-------pwd:"+pwd);
	}

}


