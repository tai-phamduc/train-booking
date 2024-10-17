package entity;

public class TrainJourney {

	private int trainJourneyID;
	private String trainJourneyName;
	private Train train;
	private double basePrice;
	private Line line;

	public TrainJourney(int trainJourneyID, String traInJourneyName, Train train, double basePrice, Line line) {
		super();
		this.trainJourneyID = trainJourneyID;
		this.trainJourneyName = traInJourneyName;
		this.train = train;
		this.basePrice = basePrice;
		this.line = line;
	}

	public TrainJourney(String tenChuyenTau, Train tau, Line duongDi, double giaGoc) {
		this.trainJourneyName = tenChuyenTau;
		this.train = tau;
		this.line = duongDi;
		this.basePrice = giaGoc;
	}

	public TrainJourney(int trainJourneyID) {
		this.trainJourneyID = trainJourneyID;
	}

	public int getTrainJourneyID() {
		return trainJourneyID;
	}

	public void setTrainJourneyID(int trainJourneyID) {
		this.trainJourneyID = trainJourneyID;
	}

	public String getTraInJourneyName() {
		return trainJourneyName;
	}

	public void setTraInJourneyName(String traInJourneyName) {
		this.trainJourneyName = traInJourneyName;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	@Override
	public String toString() {
		return "TrainJourney [trainJourneyID=" + trainJourneyID + ", traInJourneyName=" + trainJourneyName + ", train="
				+ train + ", basePrice=" + basePrice + ", line=" + line + "]";
	}

}
