package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.Train;
import entity.TrainDetails;

public class TrainDAO {
	
	private ConnectDB connectDB;
	
	public TrainDAO() {
		connectDB = ConnectDB.getInstance();
		connectDB.connect();
	}

	public List<TrainDetails> getAllTrainDetails() {
		List<TrainDetails> trainDetailsList = new ArrayList<TrainDetails>();
		Connection connection = connectDB.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.prepareStatement("SELECT TrainID, TrainNumber, NumberOfCoaches, Capacity, NumberOfCoachTypes, CoachTypes, Status FROM dbo.GetAllTrainDetails()");
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				String trainID = resultSet.getString("TrainID");
				String trainNumber = resultSet.getString("TrainNumber");
				int numberOfCoaches = resultSet.getInt("NumberOfCoaches");
				int capacity = resultSet.getInt("Capacity");
				int numberOfCoachTypes = resultSet.getInt("NumberOfCoachTypes");
				String coachTypes = resultSet.getString("CoachTypes");
				String status = resultSet.getString("Status");
				trainDetailsList.add(new TrainDetails(trainID, trainNumber, numberOfCoaches, capacity, numberOfCoachTypes, coachTypes, status));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return trainDetailsList;
	}

	public int addNewTrain(Train train) {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet generatedKeys = null;

	    try {
	        connection = connectDB.getConnection();
	        if (connection == null) {
	            System.err.println("Failed to establish a connection.");
	            return -1;
	        }

	        String sql = "INSERT INTO Train (TrainNumber, Status) VALUES (?, ?)";
	        statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	        
	        statement.setString(1, train.getTrainNumber());
	        statement.setString(2, train.getStatus());

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

	public int deleteTrainByID(String trainID) {
		Connection connection = connectDB.getConnection();
		String deleteSQL = "DELETE FROM Train WHERE trainID = ?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, trainID);
			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public Train getTrainByID(int trainID) {
		Connection connection = connectDB.getConnection();
		try {
			PreparedStatement s = connection.prepareStatement("SELECT TrainID, TrainNumber, Status FROM Train WHERE TrainID = ?");
			s.setInt(1, trainID);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				String trainNumber = rs.getString("TrainNumber");
				String status = rs.getString("Status");
				return new Train(trainID, trainNumber, status);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getNumberOfCoaches(Train train) {
		Connection connection = connectDB.getConnection();
		try {
			PreparedStatement s = connection.prepareStatement("SELECT COUNT(c.CoachID) AS NumberOfCoaches FROM Train t LEFT JOIN Coach c ON t.TrainID = c.TrainID WHERE T.TrainID = ? GROUP BY t.TrainID, t.TrainNumber");
			s.setInt(1, train.getTrainID());
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				return rs.getInt("NumberOfCoaches");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public boolean updateTrain(Train train) {
	    Connection connection = null;
	    PreparedStatement statement = null;

	    try {
	        connection = connectDB.getConnection();
	        if (connection == null) {
	            System.err.println("Failed to establish a connection.");
	            return false;
	        }

	        String sql = "UPDATE Train SET TrainNumber = ?, Status = ? WHERE TrainID = ?";
	        statement = connection.prepareStatement(sql);
	        
	        statement.setString(1, train.getTrainNumber());
	        statement.setString(2, train.getStatus());
	        statement.setInt(3, train.getTrainID());

	        int status = statement.executeUpdate();

	        if (status == 1) return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (statement != null) statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return false;
	}

	public List<TrainDetails> getTrainDetailsByTrainNumber(String trainNumberToFind) {
		Connection connection = connectDB.getConnection();
		String querySQL = "SELECT TrainID, TrainNumber, NumberOfCoaches, Capacity, NumberOfCoachTypes, CoachTypes, Status FROM dbo.GetAllTrainDetails() WHERE TrainNumber LIKE ?";
		List<TrainDetails> trainDetailsList = new ArrayList<TrainDetails>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
			preparedStatement.setString(1, "%" + trainNumberToFind + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String trainID = resultSet.getString("TrainID");
				String trainNumber = resultSet.getString("TrainNumber");
				int numberOfCoaches = resultSet.getInt("NumberOfCoaches");
				int capacity = resultSet.getInt("Capacity");
				int numberOfCoachTypes = resultSet.getInt("NumberOfCoachTypes");
				String coachTypes = resultSet.getString("CoachTypes");
				String status = resultSet.getString("Status");
				trainDetailsList.add(new TrainDetails(trainID, trainNumber, numberOfCoaches, capacity, numberOfCoachTypes, coachTypes, status));
			}
			return trainDetailsList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Train> getAllTrain() {
		Connection connection = connectDB.getConnection();
		List<Train> trainList = new ArrayList<Train>();
		try {
			PreparedStatement s = connection.prepareStatement("SELECT TrainID, TrainNumber, Status from Train");
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				int trainID = rs.getInt("TrainID");
				String trainNumber = rs.getString("TrainNumber");
				String status = rs.getString("Status");
				trainList.add(new Train(trainID, trainNumber, status));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return trainList;
	}	
	
}
