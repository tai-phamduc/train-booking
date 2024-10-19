package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import connectDB.ConnectDB;
import entity.Customer;
import entity.Employee;
import entity.TrainJourney;

public class OrderDAO {
	private ConnectDB connectDB;
	
	public OrderDAO() {
		connectDB = ConnectDB.getInstance();
		connectDB.connect();
	}

	public int themDonDat(LocalDate orderDate, String note, String paymentMethod, Customer customer, TrainJourney trainJourney,
			Employee employee) {
		Connection connection = connectDB.getConnection();
		ResultSet generatedKeys;
		try {
			PreparedStatement s = connection.prepareStatement("insert into [order] (orderdate, note, paymentMethod, customerID, trainJourneyID, employeeID) values (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			s.setDate(1, Date.valueOf(orderDate));
			s.setString(2, note);
			s.setString(3, paymentMethod);
			s.setInt(4, customer.getCustomerID());
			s.setInt(5, trainJourney.getTrainJourneyID());
			s.setString(6, employee.getEmployeeID());
			
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
