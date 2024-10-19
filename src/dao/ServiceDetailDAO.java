package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.Order;
import entity.Service;

public class ServiceDetailDAO {
	private ConnectDB connectDB;
	
	public ServiceDetailDAO() {
		connectDB = ConnectDB.getInstance();
		connectDB.connect();
	}

	public int themChiTietDichVu(Service service, Order order, int quantity) {
		Connection connection = connectDB.getConnection();
		try {
			PreparedStatement s = connection.prepareStatement("insert into ServiceDetail (serviceID, orderID, quantity) values (?, ?, ?)");
			s.setInt(1, service.getServiceID());
			s.setInt(2, order.getOrderID());
			s.setInt(3, quantity);
			int status = s.executeUpdate();
			return status;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
