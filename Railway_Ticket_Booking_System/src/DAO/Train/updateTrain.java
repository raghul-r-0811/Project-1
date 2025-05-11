package DAO.Train;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import TrainPackage.Train.Routes.trainStops;

public class updateTrain {
    public static final String URL = "jdbc:mysql://localhost:3306/p1_train";
    public static final String USER = "root";
    public static final String PASSWORD = "081101";

    public boolean addRoute(String trainID, List<trainStops> stopsList){
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD)) {
            readTrain check = new readTrain();
            if(check.isTrainID_Available(connection,trainID)){
                String insertQuery = "INSERT into routes (trainID,stopName,stop_order) VALUES (?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                for(trainStops stops : stopsList){
                    System.out.println("adding stop number :"+stops.getStopOrder());
                    preparedStatement.setString(1,trainID);
                    preparedStatement.setString(2,stops.getStopName());
                    preparedStatement.setInt(3,stops.getStopOrder());
                    preparedStatement.addBatch();
                }

                int[] result = preparedStatement.executeBatch();
                for(int i :result){
                    if(i != 1){
                        System.err.println("stop" + i +"is not added verify once again");
                        return false;
                    }
                }
                return true;
            }else{
                System.out.println("wrong train ID. Please enter correct TrainID");
            }
        } catch (SQLException e) {
            System.err.println("add route err code "+ e.getErrorCode());
            throw new RuntimeException(e);
        }
        return false;
    }
}
