package entity;

import java.util.Objects;

public class Seat {

	private int seatID;
	private int seatNumber;
	private Coach coach;

	public Seat(int seatID, int seatNumber, Coach coach) {
		super();
		this.seatID = seatID;
		this.seatNumber = seatNumber;
		this.coach = coach;
	}

	public Seat(int seatNumber, Coach coach) {
		super();
		this.seatNumber = seatNumber;
		this.coach = coach;
	}

	public int getSeatID() {
		return seatID;
	}

	public void setSeatID(int seatID) {
		this.seatID = seatID;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	@Override
	public int hashCode() {
		return Objects.hash(seatID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seat other = (Seat) obj;
		return seatID == other.seatID;
	}

}
