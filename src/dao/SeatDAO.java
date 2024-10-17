package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.Coach;
import entity.Seat;

public class SeatDAO {

	private ConnectDB connectDB;
	
	public SeatDAO() {
		connectDB = ConnectDB.getInstance();
		connectDB.connect();
	}

	public int addSeat(Seat seat) {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet generatedKeys = null;

	    try {
	        connection = connectDB.getConnection();
	        if (connection == null) {
	            System.err.println("Failed to establish a connection.");
	            return -1;
	        }

	        String sql = "INSERT INTO Seat (SeatNumber, CoachID) VALUES (?, ?)";
	        statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	        
	        statement.setInt(1, seat.getSeatNumber());
	        statement.setInt(2, seat.getCoach().getCoachID());

	        int status = statement.executeUpdate();

	        if (status == 1) {
	            generatedKeys = statement.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                return generatedKeys.getInt(1);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (generatedKeys != null) generatedKeys.close();
	            if (statement != null) statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return -1;
	}

	public List<Seat> getSeats(Coach selectedCoach) {
		Connection connection = connectDB.getConnection();
		List<Seat> seatList = new ArrayList<Seat>();
		try {
			PreparedStatement s = connection.prepareStatement("select SeatID, seatNumber, CoachID from seat where CoachID = ?");
			s.setInt(1, selectedCoach.getCoachID());
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				int seatID = rs.getInt("seatID");
				int seatNumber = rs.getInt("seatNumber");
				seatList.add(new Seat(seatID, seatNumber, selectedCoach));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seatList;
	}
	
}
