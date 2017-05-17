package edu.nclg.netctoss.util;

//测试spring-task定时任务调度之用
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestTask {

	public static void main(String[] args) {
		
		 
	    ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/spring-task.xml");  
		
		System.out.println(ctx);
	}

}
