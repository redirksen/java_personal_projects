package model;

public class PlayStatus {
	private int gameId;
	private String status;
	private int trophiesTotal;
	private int trophiesEarned;
	private String notes;

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTrophiesTotal() {
		return trophiesTotal;
	}

	public void setTrophiesTotal(int trophiesTotal) {
		this.trophiesTotal = trophiesTotal;
	}

	public int getTrophiesEarned() {
		return trophiesEarned;
	}

	public void setTrophiesEarned(int trophiesEarned) {
		this.trophiesEarned = trophiesEarned;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
