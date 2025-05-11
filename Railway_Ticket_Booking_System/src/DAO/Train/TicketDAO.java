package DAO.Train;

import TrainPackage.Train.Seat_Pack.Seat;
import TrainPackage.Train.Ticket.TrainTicket;
import TrainPackage.Train.Train_Pack.Train;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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


    public boolean bookTicket(TrainTicket trainTicket)  {
        String trainName =  "";
        // need to update ticket_no, trainName,seatNo,compartmentName
        trainTicket.setTicket_No(generateTicketNo(trainTicket));
        String  getTrainNameQuery = "SELECT Train_Name FROM passenger_train WHERE TrainID = ?";
        String selectSeatQuery ="SELECT s.seatNo, c.compName, c.compId " +
                "FROM seats s " +
                "JOIN compartments c ON s.compId= c.compId " +
                "AND  c.trainID=? AND c.type=? AND s.status='AVAILABLE' " +
                "LIMIT 1";
        try(PreparedStatement preparedStatement = connection.prepareStatement(selectSeatQuery)){
            preparedStatement.setString(1,trainTicket.getTrainID());
            preparedStatement.setString(2,trainTicket.getCoachType());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    System.out.println("seat no : "+resultSet.getInt("seatNo") +" compName :"+ resultSet.getString("compName"));
                    trainTicket.setSeatNo(resultSet.getInt("seatNo"));
                    trainTicket.setCompartmentName(resultSet.getString("compName"));
                }
            }

        }catch (SQLException e){
            System.out.println("in ticketDAO bookticket() --- "+e.getMessage());
        }
        try(PreparedStatement preparedStatement1 = connection.prepareStatement(getTrainNameQuery)){
            preparedStatement1.setString(1,trainTicket.getTrainID());
            try(ResultSet resultSet = preparedStatement1.executeQuery()) {
                if(resultSet.next()){
                    System.out.println("Train Name is "+resultSet.getString("Train_Name"));
                    trainName =  resultSet.getString("Train_Name");
                    trainTicket.setTrainName(resultSet.getString("Train_Name"));
                }
            }
        }catch (SQLException e){
            System.out.println("in in ticketDAO bookticket() getTrainName --- "+e.getMessage());
        }
        return insertTicket(trainTicket,trainName);

    }

    private boolean insertTicket(TrainTicket trainTicket, String trainName)  {
        String insertQuery = "INSERT INTO tickets (userName,userId,start,destination,ticket_no,trainID,trainName,seatNo,compartmentName,coachType)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)){
            preparedStatement.setString(1,trainTicket.getUserName());
            preparedStatement.setString(2,trainTicket.getUserId());
            preparedStatement.setString(3,trainTicket.getFrom());
            preparedStatement.setString(4,trainTicket.getTo());
            preparedStatement.setString(5,trainTicket.getTicket_No());
            preparedStatement.setString(6,trainTicket.getTrainID() );
            preparedStatement.setString(7,trainName);
            preparedStatement.setInt(8,trainTicket.getSeatNo());
            preparedStatement.setString(9,trainTicket.getCompartmentName());
            preparedStatement.setString(10,trainTicket.getCoachType());

            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                return true;
            }else{
                System.out.println(" something wrong in inserting ticket");
            }
        }catch (SQLException e){
            System.out.println("in TickeDAO insertTrain() "+ e.getMessage());
        }
        return false;
    }

    private String generateTicketNo(TrainTicket trainTicket) {

            String date = new SimpleDateFormat("yyyyMMdd").format(new Date());

            // Generate a random 4-digit number
            int randomNum = 1000 + new Random().nextInt(9999);

            // Combine all parts
            return "TN-" + date + "-" + randomNum;

    }
}
