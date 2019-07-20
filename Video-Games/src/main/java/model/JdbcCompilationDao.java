package model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcCompilationDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcCompilationDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// make list of compilation
	public List<Compilation> getCompilations() {
		return null;
	}

	// read one compliation
	public Compilation getCompilation(int compilationId) {
		return null;
	}

	// make complilation
	public void makeCompilation(Compilation compliation) {

	}
}
