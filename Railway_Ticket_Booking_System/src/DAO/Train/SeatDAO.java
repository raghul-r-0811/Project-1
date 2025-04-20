package DAO.Train;

import TrainPackage.Train.Seat_Pack.Seat;

import java.sql.Connection;
import java.sql.*;

import java.sql.PreparedStatement;
import java.util.List;

public class SeatDAO{

    public void addSeats(int compartmentId, List<Seat> seatList, Connection connection) throws SQLException {
        String inserQuery = "INSERT INTO seats (compId,seatNo,status) VALUES (?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(inserQuery,Statement.RETURN_GENERATED_KEYS)){
            for(Seat seat : seatList){
                preparedStatement.setInt(1,compartmentId);
                preparedStatement.setInt(2,seat.getSt_num());
                preparedStatement.setString(3,"AVAILABLE");
                // can add logic for berth here.
                preparedStatement.addBatch();
            }
            int[] result = preparedStatement.executeBatch();
            System.out.println(result.length +" seats added to compartment with id :"+compartmentId);
        }

    }
}
