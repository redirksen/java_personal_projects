package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcGameSystemDaoTest extends DAOIntegrationTest {

	private JdbcGameSystemDao dao;

	@Before
	public void setup() {
		dao = new JdbcGameSystemDao(this.getDataSource());

		// build test game system
		GameSystem testGameSystem = new GameSystem();
		testGameSystem.setSystemId(-1);
		testGameSystem.setSystemName("Test System");

		String sqlInsertGameSystem = "INSERT INTO game_system (system_id, system_name)" + "VALUES (?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.getDataSource());
		jdbcTemplate.update(sqlInsertGameSystem, testGameSystem.getSystemId(), testGameSystem.getSystemName());

	}

	// get all systems
	@Test
	public void get_list_of_systems() {
		GameSystem testGameSystem = new GameSystem();
		testGameSystem = makeGameSystem(-1, "Test System");

		List<GameSystem> results = dao.getAllSystems();

		assertNotNull(results);
		assertEquals(true, results.size() >= 1);
		boolean matchfound = false;
		for (GameSystem gs : results) {
			if (gs.getSystemId() == testGameSystem.getSystemId()
					&& gs.getSystemName().equalsIgnoreCase(testGameSystem.getSystemName())) {
				matchfound = true;
			}
		}
		assertEquals(true, matchfound);
	}

	// read system
	@Test
	public void test_read_one_from_database() {
		GameSystem testGameSystem = new GameSystem();
		testGameSystem = makeGameSystem(-1, "Test System");
		GameSystem results = dao.getSystem(-1);
		assertNotNull(results);
		GameSystem savedGameSystem = results;
		assertGameSystemAreEqual(testGameSystem, savedGameSystem);
	}

	// add system
	@Test
	public void create_and_read_new_status() {

		GameSystem testPlayStatus = makeGameSystem("Test System");

		dao.addSystem(testPlayStatus);
		GameSystem savedResult = dao.getSystem(testPlayStatus.getSystemId());

		assertNotEquals(null, testPlayStatus.getSystemId());
		assertGameSystemAreEqual(testPlayStatus, savedResult);
	}

	// helper methods
	private GameSystem makeGameSystem(int id, String name) {
		GameSystem theGameSystem = new GameSystem();
		theGameSystem.setSystemId(id);
		theGameSystem.setSystemName(name);

		return theGameSystem;
	}

	private GameSystem makeGameSystem(String name) {
		GameSystem theGameSystem = new GameSystem();
		theGameSystem.setSystemName(name);
		return theGameSystem;
	}

	private void assertGameSystemAreEqual(GameSystem expected, GameSystem actual) {
		assertEquals(expected.getSystemId(), actual.getSystemId());
		assertEquals(expected.getSystemName(), actual.getSystemName());

	}

}
