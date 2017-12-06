import gupta.shanu.dao.JdbcDaoImpl;
import gupta.shanu.model.Circle;

public class JdbcDemo1 {

	public static void main(String[] args) {
		Circle circle = new JdbcDaoImpl().getCircle(1);
		System.out.println(circle.getName());
	}

}
