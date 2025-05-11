package DAO.Train;

import TrainPackage.Train.Compartment_Package.Compartment;
import TrainPackage.Train.Compartment_Package.Compartment_type;

import java.awt.*;
//import java.lang.classfile.constantpool.NameAndTypeEntry;
import java.sql.*;
import java.util.List;

public class CompartmentDAO {
    String URL = "jdbc:mysql://localhost:3306/p1_train";
    String userName ="root";
    String password = "081101";

    public void addCompartment(List<Compartment> compartmentList){
        String inserQuery = "INSERT INTO compartments (trainID,compName,type) VALUES (?,?,?)";
        SeatDAO seatDAO = new SeatDAO();
        try(Connection connection = DriverManager.getConnection(URL,userName,password);
            PreparedStatement preparedStatement = connection.prepareStatement(inserQuery,PreparedStatement.RETURN_GENERATED_KEYS);){
            for(Compartment comp : compartmentList){
                preparedStatement.setString(1,comp.getTrainId() );
                preparedStatement.setString(2,comp.getCompartmentName());
                Compartment_type temp = comp.getCompartmentType();
                String compartmentType = generateCompartmentType(temp);
                preparedStatement.setString(3,compartmentType);

                // adding Compartment intoDB
                int rowsAffected = preparedStatement.executeUpdate();
                ResultSet generatedKeys;
                int generatedCompartmentId = 0;
                if(rowsAffected > 0){
                    System.out.println("Added " + comp.getCompartmentName()+ " Compartment to train " +comp.getTrainId() );
                    generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        generatedCompartmentId = generatedKeys.getInt(1);
                        System.out.println("And the compartment with id : "+generatedCompartmentId);
                    }else {
                        System.err.println("something wrong ");
                    }
                }
                // call SeatsDAO to fill the seats into the DB pass the List<Seats> to it;
                seatDAO.addSeats(generatedCompartmentId,comp.getSeatList(),connection);
            }
        }catch (SQLException e){
            System.err.println("Problem in Connecting with DB in compartment : "+e.getMessage());
            System.err.println("sql state: "+e.getSQLState());
        }
    }

    public String generateCompartmentType(Compartment_type compType){
        switch(compType){
            case AC -> {return "AC";}
            case TwoS -> {return "TwoSeater";}
            case GENERAL -> {return "General";}
            case SLEEPER -> {return "Sleeper";}
            default -> {return "";}
        }
    }

}
