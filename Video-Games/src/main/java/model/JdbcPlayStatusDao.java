package model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcPlayStatusDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcPlayStatusDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// get info
	public PlayStatus getInfo(int id) {
		PlayStatus selectedPlayStatus = new PlayStatus();
		String sqlPlayStatus = "SELECT * FROM play_status WHERE game_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlPlayStatus, id);
		while (results.next()) {
			selectedPlayStatus.setGameId(results.getInt("game_id"));
			selectedPlayStatus.setStatus(results.getString("status"));
			selectedPlayStatus.setTrophiesTotal(results.getInt("trophies_total"));
			selectedPlayStatus.setTrophiesEarned(results.getInt("trophies_earned"));
			selectedPlayStatus.setNotes(results.getString("notes"));
		}
		return selectedPlayStatus;
	}

	// update beaten/unplayed
	public void updateStatus(int id, String newGamesStatus) {
		String sqlStatus = "UPDATE play_status SET status =? WHERE game_id=?";
		jdbcTemplate.update(sqlStatus, newGamesStatus, id);

	}

	// update trophies
	public void updateTrophies(int id, int newAmount) {
		String sqlTrophies = "UPDATE play_status SET trophies_earned =? WHERE game_id=?";
		jdbcTemplate.update(sqlTrophies, newAmount, id);

	}

	// update notes
	public void updatePlayNotes(int id, String newNote) {
		String sqlPlayNotes = "UPDATE play_status SET notes =? WHERE game_id=?";
		jdbcTemplate.update(sqlPlayNotes, newNote, id);

	}

	// write info
	public void createPlayStatus(PlayStatus status) {
		String sqlInsertPlayStatus = "INSERT INTO play_status(game_id, status, trophies_total, trophies_earned, notes) "
				+ "VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlInsertPlayStatus, status.getGameId(), status.getStatus(), status.getTrophiesEarned(),
				status.getTrophiesEarned(), status.getNotes());
	}

}
