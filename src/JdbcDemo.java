import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import gupta.shanu.dao.JdbcDaoImpl;
import gupta.shanu.dao.JdbcDaoSupportImpl;
import gupta.shanu.model.Circle;

public class JdbcDemo {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		JdbcDaoSupportImpl dao = context.getBean("jdbcDaoSupportImpl",JdbcDaoSupportImpl.class);
		System.out.println(dao.getCircleCount());		
	}

}
