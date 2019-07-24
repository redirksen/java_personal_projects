package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcPlayStatusDaoTest extends DAOIntegrationTest {

	private JdbcPlayStatusDao dao;

	@Before
	public void setup() {
		dao = new JdbcPlayStatusDao(this.getDataSource());

		// build test game system
		GameSystem testGameSystem = new GameSystem();
		testGameSystem.setSystemId(-1);
		testGameSystem.setSystemName("Test System");

		String sqlInsertGameSystem = "INSERT INTO game_system (system_id, system_name)" + "VALUES (?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.getDataSource());
		jdbcTemplate.update(sqlInsertGameSystem, testGameSystem.getSystemId(), testGameSystem.getSystemName());

		// build test series
		Series testSeries = new Series();
		testSeries.setSeriesId(-1);
		testSeries.setSeriesName("Test Series");

		String sqlInsertSeries = "INSERT INTO series (series_id, series_name)" + "VALUES (?, ?)";

		jdbcTemplate = new JdbcTemplate(this.getDataSource());
		jdbcTemplate.update(sqlInsertSeries, testSeries.getSeriesId(), testSeries.getSeriesName());

		// build test compilation
		Compilation testCompilation = new Compilation();
		testCompilation.setCompilationId(-1);
		testCompilation.setCompilationName("test comp");

		String sqlInsertCompilation = "INSERT INTO compilation (compilation_id, compilation_name)" + "VALUES (?, ?)";

		jdbcTemplate = new JdbcTemplate(this.getDataSource());
		jdbcTemplate.update(sqlInsertCompilation, testCompilation.getCompilationId(),
				testCompilation.getCompilationName());

		// build test game
		Game testGame = new Game();
		testGame.setGameId(-1);
		testGame.setGameName("Test Game");
		testGame.setGameSystem(-1);
		testGame.setCompilationId(-1);
		testGame.setSeriesId(-1);
		testGame.setNotes("test game notes");

		String sqlInsertGame = "INSERT INTO game (game_id, game_name, game_system_id, compilation_id, series_id, notes)"
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		jdbcTemplate = new JdbcTemplate(this.getDataSource());
		jdbcTemplate.update(sqlInsertGame, testGame.getGameId(), testGame.getGameName(), testGame.getGameSystem(),
				testGame.getCompilationId(), testGame.getSeriesId(), testGame.getNotes());

		// build test Play Status
		PlayStatus testPlayStatus = new PlayStatus();
		testPlayStatus.setGameId(-1);
		testPlayStatus.setStatus("unbeaten");
		testPlayStatus.setTrophiesTotal(20);
		testPlayStatus.setTrophiesEarned(0);
		testPlayStatus.setNotes("play notes");

		String sqlInsertPlayStatus = "INSERT INTO play_status (game_id, status, trophies_total, trophies_earned, notes)"
				+ "VALUES (?, ?, ?, ?, ?)";

		jdbcTemplate = new JdbcTemplate(this.getDataSource());
		jdbcTemplate.update(sqlInsertPlayStatus, testPlayStatus.getGameId(), testPlayStatus.getStatus(),
				testPlayStatus.getTrophiesTotal(), testPlayStatus.getTrophiesEarned(), testPlayStatus.getNotes());

	}

	// get info
	@Test
	public void test_read_from_database() {
		PlayStatus testPlayStatus = new PlayStatus();
		testPlayStatus = makePlayStatus(-1, "unbeaten", 20, 0, "play notes");
		PlayStatus results = dao.getInfo(-1);
		assertNotNull(results);
		PlayStatus savedSeries = results;
		assertPlayStatusAreEqual(testPlayStatus, savedSeries);
	}

	// update beaten/unplayed
	@Test
	public void update_beaten_status() {
		PlayStatus testPlayStatus = new PlayStatus();
		testPlayStatus = makePlayStatus(-1, "beaten", 20, 0, "play notes");
		dao.updateStatus(-1, "beaten");
		PlayStatus results = dao.getInfo(-1);
		assertNotNull(results);
		PlayStatus savedSeries = results;
		assertPlayStatusAreEqual(testPlayStatus, savedSeries);
	}

	// update trophies
	@Test
	public void update_number_trophies_earned() {
		PlayStatus testPlayStatus = new PlayStatus();
		testPlayStatus = makePlayStatus(-1, "unbeaten", 20, 5, "play notes");
		dao.updateTrophies(-1, 5);
		PlayStatus results = dao.getInfo(-1);
		assertNotNull(results);
		PlayStatus savedSeries = results;
		assertPlayStatusAreEqual(testPlayStatus, savedSeries);
	}

	// update notes
	@Test
	public void update_play_notes() {
		PlayStatus testPlayStatus = new PlayStatus();
		testPlayStatus = makePlayStatus(-1, "unbeaten", 20, 0, "new play notes");
		dao.updatePlayNotes(-1, "new play notes");
		PlayStatus results = dao.getInfo(-1);
		assertNotNull(results);
		PlayStatus savedSeries = results;
		assertPlayStatusAreEqual(testPlayStatus, savedSeries);
	}

	// create new play status
	@Test
	public void create_and_read_new_status() {

		PlayStatus testPlayStatus = makePlayStatus("unbeaten", 20, 0, "play notes");

		dao.createPlayStatus(testPlayStatus);
		PlayStatus savedResult = dao.getInfo(testPlayStatus.getGameId());

		assertNotEquals(null, testPlayStatus.getGameId());
		assertPlayStatusAreEqual(testPlayStatus, savedResult);
	}

	// helper methods
	private PlayStatus makePlayStatus(int id, String status, int trophiesTotal, int trophiesEarned, String notes) {
		PlayStatus thePlayStatus = new PlayStatus();
		thePlayStatus.setGameId(id);
		thePlayStatus.setStatus(status);
		thePlayStatus.setTrophiesTotal(trophiesTotal);
		thePlayStatus.setTrophiesEarned(trophiesEarned);
		thePlayStatus.setNotes(notes);
		return thePlayStatus;
	}

	private PlayStatus makePlayStatus(String status, int trophiesTotal, int trophiesEarned, String notes) {
		PlayStatus thePlayStatus = new PlayStatus();
		thePlayStatus.setStatus(status);
		thePlayStatus.setTrophiesTotal(trophiesTotal);
		thePlayStatus.setTrophiesEarned(trophiesEarned);
		thePlayStatus.setNotes(notes);
		return thePlayStatus;
	}

	private void assertPlayStatusAreEqual(PlayStatus expected, PlayStatus actual) {
		assertEquals(expected.getGameId(), actual.getGameId());
		assertEquals(expected.getStatus(), actual.getStatus());
		assertEquals(expected.getTrophiesTotal(), actual.getTrophiesTotal());
		assertEquals(expected.getTrophiesEarned(), actual.getTrophiesEarned());
		assertEquals(expected.getNotes(), actual.getNotes());
	}
}
