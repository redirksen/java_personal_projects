package model;

public class CollectorsStatus {

	private int collectionId;
	private int gameId;
	private String region;
	private String physicalDigital;
	private String purchasedNewUsed;
	private String hasManual;
	private boolean collectors;
	private String editionName;

	public int getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPhysicalDigital() {
		return physicalDigital;
	}

	public void setPhysicalDigital(String physicalDigital) {
		this.physicalDigital = physicalDigital;
	}

	public String getPurchasedNewUsed() {
		return purchasedNewUsed;
	}

	public void setPurchasedNewUsed(String purchasedNewUsed) {
		this.purchasedNewUsed = purchasedNewUsed;
	}

	public String getHasManual() {
		return hasManual;
	}

	public void setHasManual(String hasManual) {
		this.hasManual = hasManual;
	}

	public boolean isCollectors() {
		return collectors;
	}

	public void setCollectors(boolean collectors) {
		this.collectors = collectors;
	}

	public String getEditionName() {
		return editionName;
	}

	public void setEditionName(String editionName) {
		this.editionName = editionName;
	}

}
