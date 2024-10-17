package entity;

import java.time.LocalDate;

public class Promotion {

	private int promotionID;
	private String description;
	private double discountPercent;
	private String status;
	private LocalDate startDate;
	private LocalDate endDate;

	public Promotion(int promotionID, String description, double discountPercent, String status, LocalDate startDate,
			LocalDate endDate) {
		super();
		this.promotionID = promotionID;
		this.description = description;
		this.discountPercent = discountPercent;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getPromotionID() {
		return promotionID;
	}

	public void setPromotionID(int promotionID) {
		this.promotionID = promotionID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
