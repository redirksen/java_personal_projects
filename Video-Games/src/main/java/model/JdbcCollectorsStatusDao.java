package model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcCollectorsStatusDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcCollectorsStatusDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// pull one by game id
	public CollectorsStatus getCollectorsStatus(int id) {
		CollectorsStatus selectedCollectorsStatus = new CollectorsStatus();
		String sqlCollectorsStatus = "SELECT * FROM collectors_status WHERE game_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlCollectorsStatus, id);
		while (results.next()) {
			selectedCollectorsStatus.setCollectionId(results.getInt("collection_id"));
			selectedCollectorsStatus.setGameId(results.getInt("game_id"));
			selectedCollectorsStatus.setRegion(results.getString("region"));
			selectedCollectorsStatus.setPhysicalDigital(results.getString("physical_digital"));
			selectedCollectorsStatus.setPurchasedNewUsed(results.getString("purchased_new_used"));
			selectedCollectorsStatus.setHasManual(results.getString("has_manual"));
			selectedCollectorsStatus.setCollectors(results.getBoolean("collectors"));
			selectedCollectorsStatus.setEditionName(results.getString("edition_name"));
		}
		return selectedCollectorsStatus;
	}

	// update manual
	public void updateHasManual(int id, String hasManual) {
		String sqlUpdateHasManual = "UPDATE collectors_status SET has_manual =? WHERE game_id=?";
		jdbcTemplate.update(sqlUpdateHasManual, hasManual, id);

	}

	// make collectors status

	public void makeCollectorsStatus(CollectorsStatus status) {
		int id = getNextId();
		String sqlInsertCollectorsStatus = "INSERT INTO collectors_status(collection_id, game_id, region, physical_digital, purchased_new_used, has_manual, collectors, edition_name) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlInsertCollectorsStatus, id, status.getGameId(), status.getRegion(),
				status.getPhysicalDigital(), status.getPurchasedNewUsed(), status.getHasManual(), status.isCollectors(),
				status.getEditionName());
		status.setCollectionId(id);
	}

	private int getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('collectors_status_collection_id_seq')";
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
