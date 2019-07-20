package model;

public class Game {

	private int gameId;
	private String gameName;
	private int compilationId;
	private int seriesId;
	private String notes;

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public int getCompilationId() {
		return compilationId;
	}

	public void setCompilationId(int compilationId) {
		this.compilationId = compilationId;
	}

	public int getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(int seriesId) {
		this.seriesId = seriesId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
