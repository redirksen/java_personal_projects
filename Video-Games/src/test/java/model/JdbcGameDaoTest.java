package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcGameDaoTest extends DAOIntegrationTest {

	private JdbcGameDao dao;

	@Before
	public void setup() {
		dao = new JdbcGameDao(this.getDataSource());

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

	}

	// make list of games
	@Test
	public void get_list_of_all_games() {
		Game testGame = new Game();
		testGame = makeGame(-1, "Test System", -1, -1, -1, "test game notes");

		List<Game> results = dao.getAllGames();

		assertNotNull(results);
		assertEquals(true, results.size() >= 1);
		boolean matchfound = false;
		for (Game g : results) {
			if (g.getGameId() == testGame.getGameId() && g.getGameName().equalsIgnoreCase(testGame.getGameName())) {
				matchfound = true;
			}
		}
		assertEquals(true, matchfound);
	}

	// make list of games by system
	@Test
	public void get_list_of_games_by_system() {
		Game testGameSystem = new Game();
		testGameSystem = makeGame(-1, "Test System", -1, -1, -1, "test game notes");

		List<Game> results = dao.getGamesbySystem(-1);

		assertNotNull(results);
		assertEquals(true, results.size() >= 1);
		boolean matchfound = false;
		for (Game g : results) {
			if (g.getGameId() == testGameSystem.getGameId()
					&& g.getGameName().equalsIgnoreCase(testGameSystem.getGameName())) {
				matchfound = true;
			}
		}
		assertEquals(true, matchfound);
	}

	// make list of games by compilation
	@Test
	public void get_list_of_games_by_comp() {
		Game testGameSystem = new Game();
		testGameSystem = makeGame(-1, "Test System", -1, -1, -1, "test game notes");

		List<Game> results = dao.getCompilationsGames(-1);

		assertNotNull(results);
		assertEquals(true, results.size() >= 1);
		boolean matchfound = false;
		for (Game g : results) {
			if (g.getGameId() == testGameSystem.getGameId()
					&& g.getGameName().equalsIgnoreCase(testGameSystem.getGameName())) {
				matchfound = true;
			}
		}
		assertEquals(true, matchfound);
	}

	// make a list of games by series
	@Test
	public void get_list_of_games_by_seris() {
		Game testGameSystem = new Game();
		testGameSystem = makeGame(-1, "Test System", -1, -1, -1, "test game notes");

		List<Game> results = dao.getSeriesGames(-1);

		assertNotNull(results);
		assertEquals(true, results.size() >= 1);
		boolean matchfound = false;
		for (Game g : results) {
			if (g.getGameId() == testGameSystem.getGameId()
					&& g.getGameName().equalsIgnoreCase(testGameSystem.getGameName())) {
				matchfound = true;
			}
		}
		assertEquals(true, matchfound);
	}

	// detail of game
	@Test
	public void test_read_from_database() {
		Game testGame = new Game();
		testGame = makeGame(-1, "Test Game", -1, -1, -1, "test game notes");
		Game results = dao.getSingleGame(-1);
		assertNotNull(results);
		Game savedSeries = results;
		assertGameAreEqual(testGame, savedSeries);
	}

	// make game
	@Test
	public void create_and_read_new_game_system() {
		Game testGame = makeGame("Test Game", -1, -1, -1, "test game notes");

		dao.makeMake(testGame);
		Game savedResult = dao.getSingleGame(testGame.getGameId());

		assertNotEquals(null, testGame.getGameId());
		assertGameAreEqual(testGame, savedResult);
	}

	// helper methods
	private Game makeGame(int id, String gameName, int gameSystem, int compilationId, int seriesId, String notes) {
		Game theGame = new Game();
		theGame.setGameId(id);
		theGame.setGameName(gameName);
		theGame.setGameSystem(gameSystem);
		theGame.setCompilationId(compilationId);
		theGame.setSeriesId(seriesId);
		theGame.setNotes(notes);
		return theGame;
	}

	private Game makeGame(String gameName, int gameSystem, int compilationId, int seriesId, String notes) {
		Game theGame = new Game();
		theGame.setGameName(gameName);
		theGame.setGameSystem(gameSystem);
		theGame.setCompilationId(compilationId);
		theGame.setSeriesId(seriesId);
		theGame.setNotes(notes);
		return theGame;
	}

	private void assertGameAreEqual(Game expected, Game actual) {
		assertEquals(expected.getGameId(), actual.getGameId());
		assertEquals(expected.getGameName(), actual.getGameName());
		assertEquals(expected.getGameSystem(), actual.getGameSystem());
		assertEquals(expected.getCompilationId(), actual.getCompilationId());
		assertEquals(expected.getSeriesId(), actual.getSeriesId());
		assertEquals(expected.getNotes(), actual.getNotes());
	}
}
