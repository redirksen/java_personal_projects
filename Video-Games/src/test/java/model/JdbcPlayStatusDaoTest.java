package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

public class JdbcPlayStatusDaoTest extends DAOIntegrationTest {

	private JdbcPlayStatusDao dao;

	@Before
	public void setup() {
		dao = new JdbcPlayStatusDao(this.getDataSource());
	}

	// get info
	// update beaten/unplayed
	// update trophies
	// update notes
	// create new play status

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
