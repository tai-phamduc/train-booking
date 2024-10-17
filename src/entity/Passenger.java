package entity;

public class Passenger {
	private int passengerID;
	private String fullName;
	private String passengerType;
	private String identificationNumber;

	public Passenger(int passengerID, String fullName, String passengerType, String identificationNumber) {
		super();
		this.passengerID = passengerID;
		this.fullName = fullName;
		this.passengerType = passengerType;
		this.identificationNumber = identificationNumber;
	}

	public int getPassengerID() {
		return passengerID;
	}

	public void setPassengerID(int passengerID) {
		this.passengerID = passengerID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassengerType() {
		return passengerType;
	}

	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	@Override
	public String toString() {
		return "Passenger [passengerID=" + passengerID + ", fullName=" + fullName + ", passengerType=" + passengerType
				+ ", identificationNumber=" + identificationNumber + "]";
	}

}
