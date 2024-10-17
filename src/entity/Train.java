package entity;

import java.util.Objects;

public class Train {
	private int trainID;
	private String trainNumber;
	private String status;

	public Train(int trainID, String trainNumber, String status) {
		super();
		this.trainID = trainID;
		this.trainNumber = trainNumber;
		this.status = status;
	}

	public Train(String soHieuTau, String trangThai) {
		this.trainNumber = soHieuTau;
		this.status = trangThai;
	}

	public Train(int trainID) {
		this.trainID = trainID;
	}
	
	public Train(int trainID, String trainNumber) {
		this.trainID = trainID;
		this.trainNumber = trainNumber;
	}

	public int getTrainID() {
		return trainID;
	}

	public void setTrainID(int trainID) {
		this.trainID = trainID;
	}

	public String getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return this.getTrainNumber();
	}

	@Override
	public int hashCode() {
		return Objects.hash(trainID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Train other = (Train) obj;
		return trainID == other.trainID;
	}

}
