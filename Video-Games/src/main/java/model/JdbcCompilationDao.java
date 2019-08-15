package model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcCompilationDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcCompilationDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// make list of compilation
	public List<Compilation> getCompilations() {
		List<Compilation> allCompilation = new ArrayList<>();
		String sqlSelectCompilation = "SELECT * FROM compilation";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectCompilation);
		while (results.next()) {
			Compilation theCompilation = new Compilation();
			theCompilation.setCompilationId(results.getInt("compilation_id"));
			theCompilation.setCompilationName(results.getString("compilation_name"));
			allCompilation.add(theCompilation);
		}
		return allCompilation;
	}

	// read one compliation
	public Compilation getCompilation(int compilationId) {
		Compilation selectedCompilation = new Compilation();
		String sqlCompilation = "SELECT * FROM compilation WHERE compilation_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlCompilation, compilationId);
		while (results.next()) {
			selectedCompilation.setCompilationId(results.getInt("compilation_id"));
			selectedCompilation.setCompilationName(results.getString("compilation_name"));
		}
		return selectedCompilation;
	}

	// make complilation
	public void makeCompilation(Compilation compliation) {
		int id = getNextId();
		String sqlInsertCompilation = "INSERT INTO compilation(compilation_id, compilation_name) VALUES (?,?)";
		jdbcTemplate.update(sqlInsertCompilation, id, compliation.getCompilationName());
		compliation.setCompilationId(id);
	}

	private int getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('compilation_compilation_id_seq')";
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
