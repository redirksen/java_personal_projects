package model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcPlayStatusDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcPlayStatusDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// get info
	public PlayStatus getInfo(int id) {
		return null;
	}

	// update beaten/unplayed
	public void updateStatus(int id, String newGamesStatus) {

	}

	// update trophies
	public void updateTrophies(int id, int newAmount) {

	}

	// update notes
	public void updatePlayNotes(int id, String newNote) {

	}

	// write info
	public void createPlayStatus(PlayStatus status) {

	}

}
