package model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcGameSystemDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcGameSystemDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// get all systems
	public List<GameSystem> getAllSystems() {
		List<GameSystem> allSystems = new ArrayList<>();
		String sqlSelectSystems = "SELECT * FROM game_system";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectSystems);
		while (results.next()) {
			GameSystem gameSystem = new GameSystem();
			gameSystem.setSystemId(results.getInt("system_id"));
			gameSystem.setSystemName(results.getString("system_name"));
			allSystems.add(gameSystem);
		}
		return allSystems;

	}

	// read system
	public GameSystem getSystem(int systemID) {
		GameSystem selectedSystem = new GameSystem();
		String sqlGameSystem = "SELECT * FROM game_system WHERE system_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGameSystem, systemID);
		while (results.next()) {
			selectedSystem.setSystemId(results.getInt("system_id"));
			selectedSystem.setSystemName(results.getString("system_name"));
		}
		return selectedSystem;
	}

	// add system
	public void addSystem(GameSystem system) {
		int id = getNextId();
		String sqlInsertResult = "INSERT INTO game_system(system_id, system_name) VALUES (?,?)";
		jdbcTemplate.update(sqlInsertResult, id, system.getSystemName());
		system.setSystemId(id);

	}

	private int getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('game_system_system_id_seq')";
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
