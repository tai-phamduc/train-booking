package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Order {

	private int orderID;
	private LocalDateTime orderDate;
	private String note;
	private String paymentMethod;
	private Customer customer;
	private TrainJourney trainJourney;
	private Employee employee;

	public Order(int orderID, LocalDateTime orderDate, String note, String paymentMethod, Customer customer,
			TrainJourney trainJourney, Employee employee) {
		super();
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.note = note;
		this.paymentMethod = paymentMethod;
		this.customer = customer;
		this.trainJourney = trainJourney;
		this.employee = employee;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public TrainJourney getTrainJourney() {
		return trainJourney;
	}

	public void setTrainJourney(TrainJourney trainJourney) {
		this.trainJourney = trainJourney;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", orderDate=" + orderDate + ", note=" + note + ", paymentMethod="
				+ paymentMethod + ", customer=" + customer + ", trainJourney=" + trainJourney + ", employee=" + employee
				+ "]";
	}

}
