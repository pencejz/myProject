package edu.nclg.netctoss.util;

//����spring-task��ʱ�������֮��
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestTask {

	public static void main(String[] args) {
		
		 
	    ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/spring-task.xml");  
		
		System.out.println(ctx);
	}

}
