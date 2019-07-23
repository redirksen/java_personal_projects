package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcCompilationDaoTest extends DAOIntegrationTest {

	private JdbcCompilationDao dao;

	@Before
	public void setup() {
		dao = new JdbcCompilationDao(this.getDataSource());

		// create test series
		Compilation testCompilation = new Compilation();
		testCompilation.setCompilationId(-1);
		;
		testCompilation.setCompilationName("Test Compliation");
		;

		String sqlInsertCompilation = "INSERT INTO compilation (compilation_id, compilation_name)" + "VALUES (?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.getDataSource());
		jdbcTemplate.update(sqlInsertCompilation, testCompilation.getCompilationId(),
				testCompilation.getCompilationId());
	}

	@Test
	public void get_list_of_all_compliation() {
		Compilation testCompilation = new Compilation();
		testCompilation = makeCompilation(-1, "Test Compliation");

		List<Compilation> results = dao.getCompilations();

		assertNotNull(results);
		assertEquals(true, results.size() >= 1);
		boolean matchfound = false;
		for (Compilation c : results) {
			if (c.getCompilationId() == testCompilation.getCompilationId()
					&& c.getCompilationName().equalsIgnoreCase(testCompilation.getCompilationName())) {
				matchfound = true;
			}
		}
		assertEquals(true, matchfound);
	}

	@Test
	public void test_read_from_database() {
		Compilation testCompilation = new Compilation();
		testCompilation = makeCompilation(-1, "Test Series");
		Compilation results = dao.getCompilation(-1);
		assertNotNull(results);
		Compilation savedSeries = results;
		assertCompilationAreEqual(testCompilation, savedSeries);
	}

	@Test
	public void test_create_new_Compilation_and_read() {

		Compilation testCompilation = makeCompilation("Test Series New Id");

		dao.makeCompilation(testCompilation);
		Compilation savedResult = dao.getCompilation(testCompilation.getCompilationId());

		assertNotEquals(null, testCompilation.getCompilationId());
		assertCompilationAreEqual(testCompilation, savedResult);

	}

	// helper methods
	private Compilation makeCompilation(int id, String name) {
		Compilation theCompilation = new Compilation();
		theCompilation.setCompilationId(id);
		theCompilation.setCompilationName(name);
		return theCompilation;
	}

	private Compilation makeCompilation(String name) {
		Compilation theCompilation = new Compilation();
		theCompilation.setCompilationName(name);
		return theCompilation;
	}

	private void assertCompilationAreEqual(Compilation expected, Compilation actual) {
		assertEquals(expected.getCompilationId(), actual.getCompilationId());
		assertEquals(expected.getCompilationName(), actual.getCompilationName());
	}
}
