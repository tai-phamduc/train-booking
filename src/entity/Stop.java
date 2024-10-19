package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Stop {

	private int stopID;
	private TrainJourney trainJourney;
	private Station station;
	private int stopOrder;
	private int distance;
	private LocalDate departureDate;
	private LocalTime arrivalTime;
	private LocalTime departureTime;

	public Stop(int stopID, TrainJourney trainJourney, Station station, int stopOrder, int distance,
			LocalDate departureDate, LocalTime arrivalTime, LocalTime departureTime) {
		super();
		this.stopID = stopID;
		this.trainJourney = trainJourney;
		this.station = station;
		this.stopOrder = stopOrder;
		this.distance = distance;
		this.departureDate = departureDate;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
	}

	public Stop(Station station, int stopOrder, int distance, LocalDate departureDate, LocalTime arrivalTime,
			LocalTime departureTime) {
		this.station = station;
		this.stopOrder = stopOrder;
		this.distance = distance;
		this.departureDate = departureDate;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
	}

	public Stop(int stopID) {
		this.stopID = stopID;
	}

	public int getStopID() {
		return stopID;
	}

	public void setStopID(int stopID) {
		this.stopID = stopID;
	}

	public TrainJourney getTrainJourney() {
		return trainJourney;
	}

	public void setTrainJourney(TrainJourney trainJourney) {
		this.trainJourney = trainJourney;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public int getStopOrder() {
		return stopOrder;
	}

	public void setStopOrder(int stopOrder) {
		this.stopOrder = stopOrder;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	@Override
	public String toString() {
		return "Stop [stopID=" + stopID + ", trainJourney=" + trainJourney + ", station=" + station + ", stopOrder="
				+ stopOrder + ", distance=" + distance + ", departureDate=" + departureDate + ", arrivalTime="
				+ arrivalTime + ", departureTime=" + departureTime + "]";
	}

}
