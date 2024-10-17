package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.Service;

public class ServiceDAO {

	private ConnectDB connectDB;
	
	public ServiceDAO() {
		connectDB = ConnectDB.getInstance();
		connectDB.connect();
	}

	public List<String> getAllServiceTypes() {
		Connection connection = connectDB.getConnection();
		List<String> serviceTypes = new ArrayList<String>();
		try {
			PreparedStatement s = connection.prepareStatement("SELECT DISTINCT type FROM Service");
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				String type = rs.getString("type");
				serviceTypes.add(type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return serviceTypes;
	}

	public List<Service> getServiceByServiceType(String loaiDichVuHienTai) {
		Connection connection = connectDB.getConnection();
		List<Service> serviceList = new ArrayList<Service>();
		try {
			PreparedStatement s = connection.prepareStatement("SELECT serviceid, servicename, price, type, imagesource, quantity FROM SERVICE WHERE TYPE = ?");
			s.setString(1, loaiDichVuHienTai);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				int serviceID = rs.getInt("serviceID");
				String serviceName = rs.getString("serviceName");
				double price = rs.getDouble("price");
				String type = rs.getString("type");
				String imageSource = rs.getString("imageSource");
				int quantity = rs.getInt("quantity");
				serviceList.add(new Service(serviceID, serviceName, price, type, imageSource, quantity));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return serviceList;
	}
}
