package model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
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
		Series selectedSeries = new Series();
		String sqlSeries = "SELECT * FROM series WHERE series_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSeries, id);
		while (results.next()) {
			selectedSeries.setSeriesId(results.getInt("series_id"));
			selectedSeries.setSeriesName(results.getString("series_name"));
		}
		return selectedSeries;
	}

	// add series
	public void createSeries(Series series) {
		int id = getNextId();
		String sqlInsertResult = "INSERT INTO series(series_id, series_name) VALUES (?,?)";
		jdbcTemplate.update(sqlInsertResult, id, series.getSeriesName());
		series.setSeriesId(id);
	}

	private int getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('series_series_id_seq')";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		Integer id = null;
		if (results.next()) {
			id = results.getInt(1);
		} else {
			throw new RuntimeException("Something strange happened, unable to select next forum post id from sequence");
		}
		return id;
	}

}
