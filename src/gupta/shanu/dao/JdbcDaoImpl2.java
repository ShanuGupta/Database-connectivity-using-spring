package gupta.shanu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import gupta.shanu.model.Circle;

@Component
public class JdbcDaoImpl2 {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Circle getCircle(int id){
		Connection conn = null;
		try{
		conn = dataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from circle where id = ?");
		ps.setInt(1, id);
		Circle circle = null;
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			circle = new Circle(id,rs.getString("name"));
		}
		rs.close();
		ps.close();
		return circle;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getCircleCount(){
		return this.jdbcTemplate.queryForObject("select count(*) from circle", Integer.class);
	}
	
	public String getCircleName(int id){
		String sql = "select name from circle where id = ?";
		return this.jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
	}
	
	public Circle getCircleById(int id){
		String sql = "select * from circle where id = ?";
		return this.jdbcTemplate.queryForObject(sql, new Object[]{id}, new CircleRowMapper());
	}
	
	public List<Circle> getAllCircles(){
		return this.jdbcTemplate.query("select * from circle", new CircleRowMapper());
	}
	
	public void insertCircle(Circle circle){
		String sql = "insert into circle(id,name) values (?,?)";
		this.jdbcTemplate.update(sql, new Object[]{circle.getId(),circle.getName()});
	}
	
	public void createTriangleTable(){
		String sql = "create table triangle(id integer, name varchar(50))";
		this.jdbcTemplate.execute(sql);
	}
	
	private static final class CircleRowMapper implements RowMapper<Circle>{
		@Override
		public Circle mapRow(ResultSet record, int rowNum) throws SQLException {
			Circle circle = new Circle();
			circle.setId(record.getInt("id"));
			circle.setName(record.getString("name"));
			return circle;
		}
	} 
}