package cn.imethan.admin.document.cms;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import cn.imethan.common.mongodb.BaseDocument;

/**
 * Article.java
 *
 * @author Ethan Wong
 * @time 2014年3月2日下午4:44:26
 */
@Document
public class Article extends BaseDocument implements Serializable{

	private static final long serialVersionUID = -8280758428618092827L;
	
	private String title;//标题
	private String content;//内容
	
	@DBRef
	private Channel channel;//栏目
	
	
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
