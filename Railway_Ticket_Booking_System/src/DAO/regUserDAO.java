package DAO;

import User.GenaralUser;
import java.sql.*;

import java.time.temporal.Temporal;

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
        String user_id = "";
        String insertQuery = "INSERT into general_user (gusername,password,email) VALUES (?,?,?)";
        try( Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
        PreparedStatement pstmt = connection.prepareStatement(insertQuery)){
        }catch (SQLException e){
            System.out.println("Error in handling database");
        }
    }


}
