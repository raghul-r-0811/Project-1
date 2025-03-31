package DAO.Train;

import TrainPackage.Train.Train_Pack.Train;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PassengerTrain_DAO implements TrainDAO{

    public static final String URL = "jdbc:mysql://localhost:3306/p1_train";
    public static final String USER = "root";
    public static final String PASSWORD = "081101";
    private static PassengerTrain_DAO temp;

    //constructor
    private PassengerTrain_DAO() {

    }
    // singleton implementation
    public static PassengerTrain_DAO getInstance(){
        if(temp == null){
            temp = new PassengerTrain_DAO();
            return temp;
        }
        return temp;
    }


    // registering part
    @Override
    public String registerTrain(String train_name,String starting_point,String destination){

        String train_id ="";
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD)){

        }catch (SQLException e){
            System.out.println("Error in connecting to database"+ e.getMessage());
        }

        return train_id;
    }

}
