package model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcCollectorsStatusDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcCollectorsStatusDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// pull one by game id
	public CollectorsStatus getCollectorsStatus(int id) {
		return null;
	}

	// update manual
	public void updateHasManual(int id, String hasManual) {

	}

	// make collectors status

	public void makeCollectorsStatus(CollectorsStatus status) {

	}

}
