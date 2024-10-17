package entity;

public class Ticket {

	private int ticketID;

	private TrainJourney trainJourney;
	private Seat seat;

	private Passenger passenger;
	private Order order;

	public Ticket(int ticketID, TrainJourney trainJourney, Seat seat, Passenger passenger, Order order) {
		super();
		this.ticketID = ticketID;
		this.trainJourney = trainJourney;
		this.seat = seat;
		this.passenger = passenger;
		this.order = order;
	}

	public int getTicketID() {
		return ticketID;
	}

	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}

	public TrainJourney getTrainJourney() {
		return trainJourney;
	}

	public void setTrainJourney(TrainJourney trainJourney) {
		this.trainJourney = trainJourney;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Ticket [ticketID=" + ticketID + ", trainJourney=" + trainJourney + ", seat=" + seat + ", passenger="
				+ passenger + ", order=" + order + "]";
	}

}
