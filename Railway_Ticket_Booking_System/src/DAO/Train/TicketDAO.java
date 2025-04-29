package DAO.Train;

import TrainPackage.Train.Ticket.TrainTicket;
import TrainPackage.Train.Train_Pack.Train;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class TicketDAO {
    public static final String URL = "jdbc:mysql://localhost:3306/p1_train";
    public static final String USER = "root";
    public static final String PASSWORD = "081101";
    public static Connection connection;

    public TicketDAO() {
        try {
            this.connection = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            System.out.println("TicketDAO "+e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Map<String,String> checkTrain_Availability(String start,String destination){
        Map<String,String> trainMap = new HashMap<>();
        String searchQuery = "SELECT t.trainID,t.Train_Name,r1.stop_order,r2.stop_order FROM routes r1\n" +
                "    JOIN routes r2 ON r1.trainID = r2.trainID\n" +
                "    JOIN p1_train.passenger_train t on r1.trainID = t.TrainID\n" +
                "WHERE r1.stopName = ?\n" +
                "  AND r2.stopName = ?;";
        try(PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
            preparedStatement.setString(1,start);
            preparedStatement.setString(2,destination);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()){
                    String trainId = resultSet.getString("trainID");
                    String trainName = resultSet.getString(2);
                    trainMap.put(trainId,trainName);
                }
            }
        }catch (SQLException e){
            System.out.println("checkTrain_Availability in TicketDAO "+e.getMessage());
        }
        if(trainMap.size() == 0){
            System.out.println(" train Map size is zero only");
        }
        return trainMap;
    }


    public boolean bookTicket(TrainTicket trainTicket) {
        return false;
    }
}
