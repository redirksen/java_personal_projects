package model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcGameSystemDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcGameSystemDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// get all systems
	public List<GameSystem> getAllSystems() {
		return null;
	}

	// read system
	public GameSystem getSystem(int systemID) {
		return null;
	}

	// add system
	public void addSystem(GameSystem system) {

	}

}
