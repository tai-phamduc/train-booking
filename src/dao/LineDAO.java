package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.Line;
import entity.Station;
import entity.Stop;
import entity.TrainJourney;

public class LineDAO {
	private ConnectDB connectDB;
	
	public LineDAO() {
		connectDB = ConnectDB.getInstance();
		connectDB.connect();
	}

	public List<Line> getAllLine() {
		Connection connection = connectDB.getConnection();
		List<Line> lineList = new ArrayList<Line>();
		try {
			PreparedStatement s = connection.prepareStatement("SELECT LineID, LineName FROM LINE");
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				int lineID = rs.getInt("LineID");
				String lineName = rs.getString("LineName");
				lineList.add(new Line(lineID, lineName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lineList;
	}

//	public List<Stop> getAllLineStop() {
//		Connection connection = connectDB.getConnection();
//		List<Stop> stopList = new ArrayList<Stop>(); 
//		try {
//			PreparedStatement s = connection.prepareStatement("select stopOrder, s.stationID, s.stationName, distance from line l join LineStop ls on l.lineID = ls.lineID join station s on ls.stationID = s.stationID where l.lineID = 1");
//			ResultSet rs = s.executeQuery();
//			while (rs.next()) {
//				int stopOrder = rs.getInt("stopOrder");
//				int stationID = rs.getInt("stationID");
//				String stationName = rs.getString("stationName");
//				int distance = rs.getInt("distance");
//				LocalDate departureDate = LocalDate.now();
//				LocalDateTime arrivalTime = LocalDateTime.now();
//				LocalDateTime departureTime = LocalDateTime.now();
//				stopList.add(new Stop(new Station(stationID, stationName), stopOrder, distance, departureDate, arrivalTime, departureTime));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return stopList;
//	}

	public List<Stop> getLineStops(int lineID) {
		Connection connection = connectDB.getConnection();
		List<Stop> stopList = new ArrayList<Stop>(); 
		try {
			PreparedStatement s = connection.prepareStatement("select stopOrder, s.stationID, s.stationName, distance from line l join LineStop ls on l.lineID = ls.lineID join station s on ls.stationID = s.stationID where l.lineID = ?");
			s.setInt(1, lineID);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				int stopOrder = rs.getInt("stopOrder");
				int stationID = rs.getInt("stationID");
				String stationName = rs.getString("stationName");
				int distance = rs.getInt("distance");
				LocalDate departureDate = LocalDate.now();
				LocalTime arrivalTime = LocalTime.now();
				LocalTime departureTime = LocalTime.now();
				stopList.add(new Stop(new Station(stationID, stationName), stopOrder, distance, departureDate, arrivalTime, departureTime));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stopList;
	}
	
	
}
