package DAO.User;

import User.GenaralUser;
import java.sql.*;

import java.util.Random;
import java.util.function.Supplier;

public class regUserDAO {
    public static final String URL = "jdbc:mysql://localhost:3306/p1_train";
    public static final String USER = "root";
    public static final String PASSWORD = "081101";

    private static regUserDAO temp;
    private GenaralUser gUser;

    private regUserDAO(GenaralUser gu){
        //using singleton
        this.gUser = gu;
    }

    public static regUserDAO getInstance(GenaralUser gu){
        if (temp == null) {
            temp = new regUserDAO(gu);
        }
        return temp;
    }

    public void register(GenaralUser user){

        String insertQuery = "INSERT into general_users (guser_id,gusername,password,email) VALUES (?,?,?,?)";
        try( Connection connection = DriverManager.getConnection(URL,USER,PASSWORD)){
            Supplier<String> userIdSupplier = () ->{
                String userId = "";
                Random random = new Random();
                boolean flag = false;
                while(!flag){
                    userId ="GU"+(100000+random.nextInt(900000));
                    String checkQuery ="SELECT 1 FROM general_users WHERE guser_id = ?";
                    try(PreparedStatement checkStatment = connection.prepareStatement(checkQuery)){
                        checkStatment.setString(1,userId);
                        try(ResultSet resultSet = checkStatment.executeQuery()){
                            flag = !resultSet.next();
                        }
                    }catch (SQLException e){
                        System.out.println(e.getMessage());
                        System.out.println("error while checking for existence of user ID ");
                    }
                }
                return userId;
            };

            String userId = userIdSupplier.get();
            try(PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)){
                preparedStatement.setString(1,userId);
                preparedStatement.setString(2,user.getgUserName());
                preparedStatement.setString(3,user.getgUserPassword());
                preparedStatement.setString(4,user.getgUserEmail());
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("General user registered with ID: " + userId);
                } else {
                    System.out.println("Failed to register user.");
                }
            }
        }catch (SQLException e){
            System.out.println("Error in connecting to database"+ e.getMessage());
        }
    }


}
