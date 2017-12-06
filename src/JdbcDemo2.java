import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import gupta.shanu.dao.JdbcDaoImpl;
import gupta.shanu.model.Circle;

public class JdbcDemo2 {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		JdbcDaoImpl dao = context.getBean("jdbcDaoImpl",JdbcDaoImpl.class);
		Circle circle = dao.getCircle(1);
		System.out.println(circle.getName());
		System.out.println(dao.getCircleCount());
		System.out.println(dao.getCircleName(1));
		System.out.println(dao.getCircleById(1).getName());
		dao.insertCircle(new Circle(4,"Fourth Circle"));
		System.out.println(dao.getAllCircles().size());		
		//dao.createTriangleTable();
	}

}
