package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.Customer;
import entity.Seat;

public class CustomerDAO {

	private ConnectDB connectDB;
	
	public CustomerDAO() {
		connectDB = ConnectDB.getInstance();
		connectDB.connect();
	}

	public int themKhachHang(Customer customer) {
		Connection connection = connectDB.getConnection();
		ResultSet generatedKeys;
		try {
			PreparedStatement s = connection.prepareStatement("Insert into customer (fullName, phoneNumber, email, identificationNumber) values (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			s.setString(1, customer.getFullName());
			s.setString(2, customer.getPhoneNumber());
			s.setString(3, customer.getEmail());
			s.setString(4, customer.getIdentificationNumber());
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
	
}
