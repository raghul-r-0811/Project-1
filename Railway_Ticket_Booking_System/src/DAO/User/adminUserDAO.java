package DAO.User;

import User.AdminUser;

import java.sql.*;

public class adminUserDAO {
    public static final String URL = "jdbc:mysql://localhost:3306/p1_train";
    public static final String USER = "root";
    public static final String PASSWORD = "081101";

    public static void register(AdminUser au){
        // adding new admin user to the database
        String insertQuery = "INSERT INTO admin_users (name, password,email) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertQuery,PreparedStatement.RETURN_GENERATED_KEYS)) {

           // pstmt.setString(1, au.getAdminID());
            pstmt.setString(1, au.getAdminName());
            pstmt.setString(2, au.getAdminPassword());// Hash password before storing in real applications
            pstmt.setString(3,au.getAdminEmail());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                try(ResultSet resultSet = pstmt.getGeneratedKeys()){
                    if (resultSet.next()) {
                        int generatedId = resultSet.getInt(1);
                        System.out.println("User registered successfully! Your Admin ID is: " + generatedId);
                    } else {
                        System.out.println("User registered, but failed to retrieve Admin ID.");
                    }
                }
                System.out.println("User registered successfully!");
            } else {
                System.out.println("Failed to register user.");

            }

        }catch(SQLException e){
            System.out.println("error in handling database");
            e.printStackTrace();
        }
    }
}
