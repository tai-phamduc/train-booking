package gui.application.form.other.train_journey;

import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entity.Stop;
import net.miginfocom.swing.MigLayout;

public class StopRow extends JPanel {
	
	private JTextField departureDateTexField;
	private JTextField arrivalTimeTextField;
	private JTextField departureTimeTextField;

	public StopRow(Stop stop) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm");
		
		this.setLayout(new MigLayout("wrap", "[grow][100px][grow][150px][100px][150px]"));
		
		JLabel stopOrderLabel = new JLabel(stop.getStopOrder() + "");
		JLabel stationLabel = new JLabel(stop.getStation().getStationName());
		JLabel distanceLabel = new JLabel(stop.getDistance() + "");
		departureDateTexField = new JTextField(stop.getDepartureDate().format(dateFormatter));
		arrivalTimeTextField = new JTextField(stop.getArrivalTime().format(timeFormatter));
		departureTimeTextField = new JTextField(stop.getDepartureTime().format(timeFormatter));
		
		this.add(stopOrderLabel);
		this.add(stationLabel);
		this.add(distanceLabel);
		this.add(departureDateTexField, "growx");
		this.add(arrivalTimeTextField, "growx");
		this.add(departureTimeTextField, "growx");
	}

	public JTextField getDepartureDateTexField() {
		return departureDateTexField;
	}

	public void setDepartureDateTexField(JTextField departureDateTexField) {
		this.departureDateTexField = departureDateTexField;
	}

	public JTextField getArrivalTimeTextField() {
		return arrivalTimeTextField;
	}

	public void setArrivalTimeTextField(JTextField arrivalTimeTextField) {
		this.arrivalTimeTextField = arrivalTimeTextField;
	}

	public JTextField getDepartureTimeTextField() {
		return departureTimeTextField;
	}

	public void setDepartureTimeTextField(JTextField departureTimeTextField) {
		this.departureTimeTextField = departureTimeTextField;
	}
	
}
