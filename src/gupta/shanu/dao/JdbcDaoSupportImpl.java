/**
 * 
 */
package gupta.shanu.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @author I324735
 *
 */
public class JdbcDaoSupportImpl extends JdbcDaoSupport {
	public int getCircleCount(){
		return this.getJdbcTemplate().queryForObject("select count(*) from circle", Integer.class);
	}
}
