package model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcSeriesDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcSeriesDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// get info from database
	public Series getSeries(int id) {
		return null;
	}

	// add series
	public void createSeries(Series series) {

	}

}
