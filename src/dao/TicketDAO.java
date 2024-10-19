package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.Order;
import entity.Passenger;
import entity.Seat;
import entity.TrainJourney;

public class TicketDAO {
	private ConnectDB connectDB;
	
	public TicketDAO() {
		connectDB = ConnectDB.getInstance();
		connectDB.connect();
	}

	public int themVe(TrainJourney trainJourney, Seat seat, Passenger passenger, Order order) {
		Connection connection = connectDB.getConnection();
		ResultSet generatedKeys;
		try {
			PreparedStatement s = connection.prepareStatement("insert into ticket (trainJourneyID, seatID, passengerID, orderID) values (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			s.setInt(1, trainJourney.getTrainJourneyID());
			s.setInt(2, seat.getSeatID());
			s.setInt(3, passenger.getPassengerID());
			s.setInt(4, order.getOrderID());
			int status = s.executeUpdate();
			if (status == 1) {
				generatedKeys = s.getGeneratedKeys();
				if (generatedKeys.next()) {
					return generatedKeys.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
