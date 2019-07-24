package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcCollectorsStatusDaoTest extends DAOIntegrationTest {

	private JdbcCollectorsStatusDao dao;

	@Before
	public void setup() {
		dao = new JdbcCollectorsStatusDao(this.getDataSource());

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

		// build test Collection Status
		CollectorsStatus testCollectorsStatus = new CollectorsStatus();
		testCollectorsStatus.setCollectionId(-1);
		testCollectorsStatus.setGameId(-1);
		testCollectorsStatus.setRegion("USA");
		testCollectorsStatus.setPhysicalDigital("Physical");
		testCollectorsStatus.setPurchasedNewUsed("New");
		testCollectorsStatus.setHasManual("yes");
		testCollectorsStatus.setCollectors(true);
		testCollectorsStatus.setEditionName("Test Collectors");

		String sqlInsertCollectorsStatus = "INSERT INTO collectors_status (collection_id, game_id, region, physical_digital, purchased_new_used, has_manual, collectors, edition_name)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		jdbcTemplate = new JdbcTemplate(this.getDataSource());
		jdbcTemplate.update(sqlInsertCollectorsStatus, testCollectorsStatus.getCollectionId(),
				testCollectorsStatus.getGameId(), testCollectorsStatus.getRegion(),
				testCollectorsStatus.getPhysicalDigital(), testCollectorsStatus.getPurchasedNewUsed(),
				testCollectorsStatus.getHasManual(), testCollectorsStatus.isCollectors(),
				testCollectorsStatus.getEditionName());

	}

	// pull one by game id
	@Test
	public void test_read_from_database() {
		CollectorsStatus testCollectorsStatus = new CollectorsStatus();
		testCollectorsStatus = makeCollectorsStatus(-1, -1, "USA", "Physical", "New", "yes", true, "Test Collectors");
		CollectorsStatus results = dao.getCollectorsStatus(-1);
		assertNotNull(results);
		CollectorsStatus savedCollectorsStatus = results;
		assertCollectorsStatusAreEqual(testCollectorsStatus, savedCollectorsStatus);
	}

	// update manual
	@Test
	public void update_has_manual() {
		CollectorsStatus testCollectorsStatus = new CollectorsStatus();
		testCollectorsStatus = makeCollectorsStatus(-1, -1, "USA", "Physical", "New", "yes", true, "Test Collectors");
		dao.updateHasManual(-1, "yes");
		CollectorsStatus results = dao.getCollectorsStatus(-1);
		assertNotNull(results);
		CollectorsStatus savedCollectorsStatus = results;
		assertCollectorsStatusAreEqual(testCollectorsStatus, savedCollectorsStatus);
	}

	// make collectors status
	@Test
	public void create_and_read_new_status() {

		CollectorsStatus testCollectorsStatus = makeCollectorsStatus(-1, "USA", "Physical", "New", "yes", true,
				"Test Collectors");

		dao.makeCollectorsStatus(testCollectorsStatus);
		CollectorsStatus savedResult = dao.getCollectorsStatus(testCollectorsStatus.getCollectionId());

		assertNotEquals(null, testCollectorsStatus.getGameId());
		assertCollectorsStatusAreEqual(testCollectorsStatus, savedResult);
	}

	// helper methods
	private CollectorsStatus makeCollectorsStatus(int id, int gameId, String region, String physical, String newUsed,
			String haveManual, boolean collectors, String name) {
		CollectorsStatus theCollectorsStatus = new CollectorsStatus();
		theCollectorsStatus.setCollectionId(id);
		theCollectorsStatus.setGameId(gameId);
		theCollectorsStatus.setRegion(region);
		theCollectorsStatus.setPhysicalDigital(physical);
		theCollectorsStatus.setPurchasedNewUsed(newUsed);
		theCollectorsStatus.setHasManual(haveManual);
		theCollectorsStatus.setCollectors(collectors);
		theCollectorsStatus.setEditionName(name);
		return theCollectorsStatus;
	}

	private CollectorsStatus makeCollectorsStatus(int gameId, String region, String physical, String newUsed,
			String haveManual, boolean collectors, String name) {
		CollectorsStatus theCollectorsStatus = new CollectorsStatus();
		theCollectorsStatus.setGameId(gameId);
		theCollectorsStatus.setRegion(region);
		theCollectorsStatus.setPhysicalDigital(physical);
		theCollectorsStatus.setPurchasedNewUsed(newUsed);
		theCollectorsStatus.setHasManual(haveManual);
		theCollectorsStatus.setCollectors(collectors);
		theCollectorsStatus.setEditionName(name);
		return theCollectorsStatus;
	}

	private void assertCollectorsStatusAreEqual(CollectorsStatus expected, CollectorsStatus actual) {
		assertEquals(expected.getCollectionId(), actual.getCollectionId());
		assertEquals(expected.getGameId(), actual.getGameId());
		assertEquals(expected.getRegion(), actual.getRegion());
		assertEquals(expected.getPhysicalDigital(), actual.getPhysicalDigital());
		assertEquals(expected.getPurchasedNewUsed(), actual.getPurchasedNewUsed());
		assertEquals(expected.getHasManual(), actual.getHasManual());
		assertEquals(expected.isCollectors(), actual.isCollectors());
		assertEquals(expected.getEditionName(), actual.getEditionName());
	}

}
