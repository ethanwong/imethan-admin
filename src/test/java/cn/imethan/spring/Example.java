package cn.imethan.spring;

import org.springframework.context.MessageSource;

/**
 * Example.java
 *
 * @author Ethan Wong
 * @time 2015年11月4日下午1:15:09
 */
public class Example {
	private MessageSource messages;

    public void setMessages(MessageSource messages) {
        this.messages = messages;
    }

    public void execute() {
        String message = this.messages.getMessage("argument.required",
            new Object [] {"userDao"}, "Required", null);
        System.out.println(message);
    }

}


