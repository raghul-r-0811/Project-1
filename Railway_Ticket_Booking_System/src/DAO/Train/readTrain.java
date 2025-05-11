package DAO.Train;

import java.lang.invoke.StringConcatFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class readTrain {

    public boolean isTrainID_Available(Connection connection,String trainID) throws SQLException {
        System.out.println("checking if train is there or not");
        String searchQuery = "SELECT 1 FROM passenger_train WHERE TrainID = ? LIMIT 1";
       try(PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);){
           preparedStatement.setString(1,trainID);

           try(ResultSet resultSet = preparedStatement.executeQuery()){
               if(resultSet.next()){
                   System.out.println("yep train is there");
                   return true;
               }

           }
       }catch (SQLException e){
           System.err.println("in isTrainID_Available method error code:"+e.getErrorCode());
           System.err.println(e.getSQLState());
           System.err.println(e.getMessage());
       }
        return false;
    }

}
