package model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcGameDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcGameDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// make list of games
	public List<Game> getAllGames() {
		return null;
	}

	// make list of games by system
	public List<Game> getGamesbySystem(int systemId) {
		return null;
	}

	// make list of games by compilation
	public List<Game> getCompilationsGames(int compilationId) {
		return null;
	}

	// make a list of games by series
	public List<Game> getSeriesGames(int seriesId) {
		return null;
	}

	// detail of game
	public Game getSingleGame(int gameId) {
		return null;
	}

	// make game
	public void makeMake(Game game) {

	}

}
