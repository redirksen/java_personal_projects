package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcSeriesDaoTest extends DAOIntegrationTest {

	private JdbcSeriesDao dao;

	@Before
	public void setup() {
		dao = new JdbcSeriesDao(this.getDataSource());

		// create test series
		Series testSeries = new Series();
		testSeries.setSeriesId(-1);
		testSeries.setSeriesName("Test Series");

		String sqlInsertSeries = "INSERT INTO series (series_id, series_name)" + "VALUES (?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.getDataSource());
		jdbcTemplate.update(sqlInsertSeries, testSeries.getSeriesId(), testSeries.getSeriesName());
	}

	@Test
	public void test_read_from_database() {
		Series testSeries = new Series();
		testSeries = makeSeries(-1, "Test Series");
		Series results = dao.getSeries(-1);
		assertNotNull(results);
		Series savedSeries = results;
		assertSeriesAreEqual(testSeries, savedSeries);
	}

	@Test
	public void test_create_new_Series_and_read() {

		Series testSeries = makeSeries("Test Series New Id");

		dao.createSeries(testSeries);
		Series savedResult = dao.getSeries(testSeries.getSeriesId());

		assertNotEquals(null, testSeries.getSeriesId());
		assertSeriesAreEqual(testSeries, savedResult);

	}

	// helper methods
	private Series makeSeries(int id, String name) {
		Series theSeries = new Series();
		theSeries.setSeriesId(id);
		theSeries.setSeriesName(name);
		return theSeries;
	}

	private Series makeSeries(String name) {
		Series theSeries = new Series();
		theSeries.setSeriesName(name);
		return theSeries;
	}

	private void assertSeriesAreEqual(Series expected, Series actual) {
		assertEquals(expected.getSeriesId(), actual.getSeriesId());
		assertEquals(expected.getSeriesName(), actual.getSeriesName());
	}

}
