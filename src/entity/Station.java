package entity;

public class Station {

	private int stationID;
	private String stationName;

	public Station(int stationID, String stationName) {
		super();
		this.stationID = stationID;
		this.stationName = stationName;
	}

	public int getStationID() {
		return stationID;
	}

	public void setStationID(int stationID) {
		this.stationID = stationID;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	@Override
	public String toString() {
		return "Station [stationID=" + stationID + ", stationName=" + stationName + "]";
	}

}
