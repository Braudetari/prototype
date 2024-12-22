package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import common.Subscriber;

public class DatabaseConnection {
    public static Connection connectToDB(String DBIp, String DBScheme, String DBUser, String DBPass) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("Driver definition succeeded.");
        } catch (Exception ex) {
            System.out.println("Driver definition failed: " + ex.getMessage());
            return null;
        }
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://"+DBIp+"/"+DBScheme+"?serverTimezone=IST", 
                DBUser,
                DBPass
            );
            System.out.println("SQL connection succeeded.");
            return conn;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    }

    public static ArrayList<Subscriber> getAllSubscribers(Connection connection) {
        ArrayList<Subscriber> subscribers = new ArrayList<>();
        
        if (connection == null) {
            System.out.println("Failed to connect to the database.");
            return subscribers;
        }
        
        try (Statement stmt = connection.createStatement()) {
            String query = "SELECT * FROM subscriber";
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                int subscriberId = rs.getInt("subscriber_id");
                String subscriberName = rs.getString("subscriber_name");
                int detailedSubscriptionHistory = rs.getInt("detailed_subscription_history");
                String subscriberPhoneNumber = rs.getString("subscriber_phone_number");
                String subscriberEmail = rs.getString("subscriber_email");
                
                // Create Subscriber object and add to the list
                Subscriber subscriber = new Subscriber(subscriberId, subscriberName, detailedSubscriptionHistory, subscriberPhoneNumber, subscriberEmail);
                subscribers.add(subscriber);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
        
        return subscribers;
    }
    
    public static Subscriber getSubscriber(Connection connection, int subscriberId) {
    	return null;
    }

    public static boolean updateSubscriber(Connection connection, int subscriberId, String newEmail, String newPhoneNumber) {
        
        if (connection == null) {
            System.out.println("Failed to connect to the database.");
            return false;
        }
        
        Subscriber updatedSubscriber = null;
        
        try {
            String query = "UPDATE subscriber SET subscriber_phone_number = ?, subscriber_email = ? WHERE subscriber_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, newPhoneNumber);
            pstmt.setString(2, newEmail);
            pstmt.setInt(3, subscriberId);
            
            int rowsAffected = pstmt.executeUpdate();
            return (rowsAffected > 0) ? true : false;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            return false;
        }
    }

}
