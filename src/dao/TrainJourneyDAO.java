package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.Line;
import entity.Seat;
import entity.Station;
import entity.Stop;
import entity.Train;
import entity.TrainJourney;
import entity.TrainJourneyDetails;
import entity.TrainJourneyOptionItem;

public class TrainJourneyDAO {
	private ConnectDB connectDB;
	
	public TrainJourneyDAO() {
		connectDB = ConnectDB.getInstance();
		connectDB.connect();
	}

	public List<TrainJourneyDetails> getAllTrainJourneyDetails() {
		Connection connection = connectDB.getConnection();
		List<TrainJourneyDetails> trainJourneyDetailsList = new ArrayList<TrainJourneyDetails>();
		ResultSet rs = null;
		try {
			PreparedStatement s = connection.prepareStatement("SELECT trainJourneyID, trainJourneyName, trainNumber, departureStation, arrivalStation, departureDate, departureTime, arrivalTime, totalDistance, bookedTickets, totalSeats FROM dbo.fn_GetAllTrainJourneyDetails();");
			rs = s.executeQuery();
			while (rs.next()) {
				int trainJourneyID = rs.getInt("trainJourneyID");
				String trainJourneyName = rs.getString("trainJourneyName");
				String trainNumber = rs.getString("trainNumber");
				String departureStation = rs.getString("departureStation");
				String arrivalStation = rs.getString("arrivalStation");
				LocalDateTime departureTime = rs.getTimestamp("departureTime").toLocalDateTime();
				LocalDate departureDate = rs.getDate("departureDate").toLocalDate();
				LocalDateTime arrivalTime = rs.getTimestamp("arrivalTime").toLocalDateTime();
				int totalDistance = rs.getInt("totalDistance");
				int bookedTickets = rs.getInt("bookedTickets");
				int totalSeats = rs.getInt("totalSeats");
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
				
				trainJourneyDetailsList.add(new TrainJourneyDetails(trainJourneyID, trainNumber, trainJourneyName, departureStation + " - " + arrivalStation, departureDate.format(dateFormatter), departureTime.format(formatter) + " - " + arrivalTime.format(formatter), totalDistance, bookedTickets + "/" + totalSeats));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trainJourneyDetailsList;
	}

	public int addTrainJourney(TrainJourney trainJourney) {
		
		Connection connection = connectDB.getConnection();
		ResultSet generatedKeys = null;
		
		try {
			PreparedStatement s = connection.prepareStatement("INSERT INTO TrainJourney (trainJourneyName, trainID, lineID, basePrice) VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			s.setString(1, trainJourney.getTraInJourneyName());
			s.setInt(2, trainJourney.getTrain().getTrainID());
			s.setInt(3,  trainJourney.getLine().getLineID());
			s.setDouble(4, trainJourney.getBasePrice());
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

	public boolean addStops(List<Stop> stopList) {
	    Connection connection = null;
	    PreparedStatement s = null;
	    try {
	        // Get the database connection
	        connection = connectDB.getConnection();
	        connection.setAutoCommit(false); // Disable auto-commit for batch insertion

	        // Prepare the SQL statement
	        s = connection.prepareStatement(
	                "INSERT INTO Stop (trainJourneyID, stationID, stopOrder, distance, departureDate, arrivalTime, departureTime) " +
	                "VALUES (?, ?, ?, ?, ?, ?, ?)");

	        // Loop through the list of stops and add them to the batch
	        for (Stop stop : stopList) {
	            s.setInt(1, stop.getTrainJourney().getTrainJourneyID());
	            s.setInt(2, stop.getStation().getStationID());
	            s.setInt(3, stop.getStopOrder());
	            s.setInt(4, stop.getDistance());
	            s.setDate(5, java.sql.Date.valueOf(stop.getDepartureDate())); // Convert LocalDate to java.sql.Date
	            s.setTime(6, java.sql.Time.valueOf(stop.getArrivalTime()));   // Convert LocalTime to java.sql.Time
	            s.setTime(7, java.sql.Time.valueOf(stop.getDepartureTime())); // Convert LocalTime to java.sql.Time

	            s.addBatch(); // Add the statement to the batch
	        }

	        // Execute the batch and commit the transaction
	        s.executeBatch();
	        connection.commit(); // Commit all changes

	        return true; // Return true if successful

	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (connection != null) connection.rollback(); // Roll back on error
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	    } finally {
	        // Close the resources
	        try {
	            if (s != null) s.close();
	        } catch (SQLException closeEx) {
	            closeEx.printStackTrace();
	        }
	    }
	    return false; // Return false if an error occurred
	}

	public int deleteTrainJourneyByID(int trainJourneyID) {
	    Connection connection = null;
	    PreparedStatement deleteStopsStmt = null;
	    PreparedStatement deleteTrainJourneyStmt = null;

	    try {
	        // Get the connection
	        connection = connectDB.getConnection();
	        connection.setAutoCommit(false); // Disable auto-commit for transaction

	        // Delete related stops
	        String deleteStopsSQL = "DELETE FROM Stop WHERE trainJourneyID = ?";
	        deleteStopsStmt = connection.prepareStatement(deleteStopsSQL);
	        deleteStopsStmt.setInt(1, trainJourneyID);
	        int stopsDeleted = deleteStopsStmt.executeUpdate();

	        // Delete the train journey
	        String deleteTrainJourneySQL = "DELETE FROM TrainJourney WHERE trainJourneyID = ?";
	        deleteTrainJourneyStmt = connection.prepareStatement(deleteTrainJourneySQL);
	        deleteTrainJourneyStmt.setInt(1, trainJourneyID);
	        int trainJourneyDeleted = deleteTrainJourneyStmt.executeUpdate();

	        // Commit the transaction
	        connection.commit();

	        // Return the total number of deleted records
	        return stopsDeleted + trainJourneyDeleted;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (connection != null) {
	                connection.rollback(); // Roll back if there's an error
	            }
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	    } finally {
	        // Close resources to avoid memory leaks
	        try {
	            if (deleteStopsStmt != null) deleteStopsStmt.close();
	            if (deleteTrainJourneyStmt != null) deleteTrainJourneyStmt.close();
	        } catch (SQLException closeEx) {
	            closeEx.printStackTrace();
	        }
	    }
	    return 0; // Return 0 if the operation failed
	}

	public TrainJourney getTrainJourneyByID(int trainJourneyID) {
		Connection connection = connectDB.getConnection();
		try {
			PreparedStatement s = connection.prepareStatement("SELECT trainJourneyName, t.trainID, t.trainNumber, t.Status, l.lineID, l.lineName, basePrice FROM TrainJourney tj join Train t on tj.trainID = t.TrainID join line l on tj.lineID = l.lineID WHERE trainJourneyID = ?");
			s.setInt(1, trainJourneyID);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				String trainJourneyName = rs.getString("trainJourneyName");
				Train train = new Train(rs.getInt("trainID"), rs.getString("trainNumber"), rs.getString("status"));
				Line line = new Line(rs.getInt("lineID"), rs.getString("lineName"));
				double basePrice = rs.getDouble("basePrice");
				return new TrainJourney(trainJourneyID, trainJourneyName, train, basePrice, line);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Stop> getAllStops(TrainJourney trainJourney) {
		Connection connection = connectDB.getConnection();
		List<Stop> stopList = new ArrayList<Stop>(); 
		try {
			PreparedStatement s = connection.prepareStatement("select stopID, trainJourneyID, station.stationID, station.stationName, stopOrder, distance, departureDate, arrivalTime, departureTime from stop join Station on stop.stationID = Station.stationID where trainJourneyID = ?");
			s.setInt(1, trainJourney.getTrainJourneyID());
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				int stopID = rs.getInt("stopID");
				int stopOrder = rs.getInt("stopOrder");
				int stationID = rs.getInt("stationID");
				String stationName = rs.getString("stationName");
				int distance = rs.getInt("distance");
				LocalDate departureDate = rs.getDate("departureDate").toLocalDate();
				LocalTime arrivalTime = rs.getTime("arrivalTime").toLocalTime();
				LocalTime departureTime = rs.getTime("departureTime").toLocalTime();
				stopList.add(new Stop(stopID, trainJourney, new Station(stationID, stationName), stopOrder, distance, departureDate, arrivalTime, departureTime));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stopList;
	}

	public int updateTrainJourney(TrainJourney trainJourney) {
		Connection connection = connectDB.getConnection();
		try {
			PreparedStatement s = connection.prepareStatement("update TrainJourney set trainJourneyName = ?, basePrice = ? where trainJourneyID = ?");
			s.setString(1, trainJourney.getTraInJourneyName());
			s.setDouble(2, trainJourney.getBasePrice());
			s.setInt(3, trainJourney.getTrainJourneyID());
			return s.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public List<TrainJourneyDetails> getAllTrainJourneyDetailsByTrainNumber(String trainNumberToFind) {
		Connection connection = connectDB.getConnection();
		List<TrainJourneyDetails> trainJourneyDetailsList = new ArrayList<TrainJourneyDetails>();
		ResultSet rs = null;
		try {
			PreparedStatement s = connection.prepareStatement("SELECT trainJourneyID, trainJourneyName, trainNumber, departureStation, arrivalStation, departureDate, departureTime, arrivalTime, totalDistance, bookedTickets, totalSeats FROM dbo.fn_GetAllTrainJourneyDetails() where trainNumber LIKE ?");
			s.setString(1, "%" + trainNumberToFind + "%");
			rs = s.executeQuery();
			while (rs.next()) {
				int trainJourneyID = rs.getInt("trainJourneyID");
				String trainJourneyName = rs.getString("trainJourneyName");
				String trainNumber = rs.getString("trainNumber");
				String departureStation = rs.getString("departureStation");
				String arrivalStation = rs.getString("arrivalStation");
				LocalDateTime departureTime = rs.getTimestamp("departureTime").toLocalDateTime();
				LocalDate departureDate = rs.getDate("departureDate").toLocalDate();
				LocalDateTime arrivalTime = rs.getTimestamp("arrivalTime").toLocalDateTime();
				int totalDistance = rs.getInt("totalDistance");
				int bookedTickets = rs.getInt("bookedTickets");
				int totalSeats = rs.getInt("totalSeats");
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
				
				trainJourneyDetailsList.add(new TrainJourneyDetails(trainJourneyID, trainNumber, trainJourneyName, departureStation + " - " + arrivalStation, departureDate.format(dateFormatter), departureTime.format(formatter) + " - " + arrivalTime.format(formatter), totalDistance, bookedTickets + "/" + totalSeats));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trainJourneyDetailsList;
	}

	public List<TrainJourneyOptionItem> searchTrainJourney(String gaDi, String gaDen, LocalDate ngayDi) {
		Connection connection = connectDB.getConnection();
		List<TrainJourneyOptionItem> trainJourneyOptionItemList = new ArrayList<TrainJourneyOptionItem>();
		try {
			PreparedStatement s = connection.prepareStatement("SELECT * FROM dbo.GetTrainJourneysByStationNames(?, ?, ?)");
			s.setString(1, gaDi);
			s.setString(2, gaDen);
			s.setDate(3, Date.valueOf(ngayDi));
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				int trainJourneyID = rs.getInt("trainJourneyID");
				Train train = new Train(rs.getInt("trainID"), rs.getString("trainNumber"));
				int numberOfAvailableSeatsLeft = rs.getInt("numberOfAvailableSeatsLeft");
				LocalDate departureDate = rs.getDate("departureDate").toLocalDate();
				LocalTime depatureTime = rs.getTime("departureTime").toLocalTime();
				LocalDate arrivalDate = rs.getDate("arrivalDate").toLocalDate();
				LocalTime arrivalTime = rs.getTime("arrivalTime").toLocalTime();
				int journeyDuration = rs.getInt("journeyDuration");	
				Station departureStation = new Station(rs.getInt("departureStationID"), rs.getString("departureStationName"));
				Station arrivalStation = new Station(rs.getInt("arrivalStationID"), rs.getString("arrivalStationName"));
				trainJourneyOptionItemList.add(new TrainJourneyOptionItem(trainJourneyID, train, numberOfAvailableSeatsLeft, departureDate, depatureTime, arrivalDate, arrivalTime, journeyDuration, departureStation, arrivalStation));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trainJourneyOptionItemList;
	}

	public int getDistanceBetweenTwoStopsOfATrainJourney(int trainJourneyID, Station departureStation, Station arrivalStation) {
		Connection connection = connectDB.getConnection();
		try {
			PreparedStatement s = connection.prepareStatement("SELECT dbo.GetDistanceBetweenStops(?, ?, ?) AS distance");
			s.setInt(1, trainJourneyID);
			s.setInt(2, departureStation.getStationID());
			s.setInt(3, arrivalStation.getStationID());
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				return rs.getInt("distance");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public List<Stop> getStops(TrainJourney trainJourney, Station departureStation, Station arrivalStation) {
		Connection connection = connectDB.getConnection();
		List<Stop> stopList = new ArrayList<Stop>();
		try {
			PreparedStatement s = connection.prepareStatement("select stopid from dbo.GetStopsForJourney(?, ?, ?)");
			s.setInt(1, trainJourney.getTrainJourneyID());
			s.setInt(2, departureStation.getStationID());
			s.setInt(3, arrivalStation.getStationID());
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				int stopID = rs.getInt("stopid");
				stopList.add(new Stop(stopID));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stopList;
	}

	public List<Seat> getUnavailableSeats(int trainJourneyID, Station departureStation, Station arrivalStation) {
		Connection connection = connectDB.getConnection();
		List<Seat> seatList = new ArrayList<Seat>();
		try {
			PreparedStatement s = connection.prepareStatement("select SeatID, SeatNumber FROM dbo.fn_GetUnavailableSeats(?, ?, ?)");
			s.setInt(1, trainJourneyID);
			s.setInt(2, departureStation.getStationID());
			s.setInt(3, arrivalStation.getStationID());
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				int seatID = rs.getInt("seatID");
				int seatNumber = rs.getInt("seatNumber");
				seatList.add(new Seat(seatID, seatNumber));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seatList;
	}
	
}
