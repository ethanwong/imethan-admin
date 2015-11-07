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
		String pwd = bCryptPasswordEncoder.encode("e10adc3949ba59abbe56e057f20f883e");
		System.out.println("-------pwd:"+pwd);
		boolean isMatches = bCryptPasswordEncoder.matches("123456", "$2a$10$F5myTdSJLC.LfTQOpMWnu.sJzqgcMgKtyunYTq/9T41UIHGjDANw6");
		System.out.println("-------isMatches:"+isMatches);
	}

}


