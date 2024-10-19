package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.Passenger;

public class PassengerDAO {
	private ConnectDB connectDB;
	
	public PassengerDAO() {
		connectDB = ConnectDB.getInstance();
		connectDB.connect();
	}

	public int themHanhKhach(Passenger passenger) {
		Connection connection = connectDB.getConnection();
		ResultSet generatedKeys;
		try {
			PreparedStatement s = connection.prepareStatement("insert into Passenger (fullName, passengerType, identificationNumber) values (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			s.setString(1, passenger.getFullName());
			s.setString(2, passenger.getPassengerType());
			s.setString(3, passenger.getIdentificationNumber());
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
