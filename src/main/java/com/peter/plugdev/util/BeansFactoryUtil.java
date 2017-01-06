package com.peter.plugdev.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.peter.plugdev.mapper.PlugMapper;

public class BeansFactoryUtil {
	private static ApplicationContext ac = null;
	
	static {		
		ac=new ClassPathXmlApplicationContext(new String[]{"spring-dao.xml"});
	}
	
	public static Object getBeanObject(String bean){
		
		return ac.getBean(bean);
	}
	
}
