package DAO.User;

import User.GenaralUser;

import java.sql.*;
import java.util.SortedMap;

public class readUser {
    public static final String URL = "jdbc:mysql://localhost:3306/p1_train";
    public static final String USER = "root";
    public static final String PASSWORD = "081101";


    public boolean checkIsGeneralUserTrue(String id, String password, GenaralUser user) {
        String searchQuery = "SELECT * from general_users WHERE guser_id = ? AND password = ?";
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);) {
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                return false;
            }

            //System.out.println(resultSet.getString("email"));
            //System.out.println(resultSet.getString("guser_id"));
            //System.out.println(resultSet.getString("gusername"));
            user.setgUserEmail(resultSet.getString("email"));
            user.setgUserid(resultSet.getString("guser_id"));
            user.setgUserName(resultSet.getString("gusername"));
        }catch (SQLException e){
            System.out.println("in readUser.checkIsGeneralUserTrue : "+e.getMessage());
        }
        return true;
    }
}
