package cn.imethan.admin.document.cms;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import cn.imethan.common.mongodb.BaseDocument;


/**
 * Channel.java
 *
 * @author Ethan Wong
 * @time 2014年3月2日下午4:44:32
 */
@Document
public class Channel extends BaseDocument implements Serializable{

	private static final long serialVersionUID = -3919470868083141326L;
	
	private String title;//标题
	private String describe;//描述

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}

}
