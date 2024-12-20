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
    public static Connection connectToDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("Driver definition succeeded.");
        } catch (Exception ex) {
            System.out.println("Driver definition failed: " + ex.getMessage());
            return null;
        }
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://5.29.139.39/prototypedb?serverTimezone=IST", 
                "admin", 
                "braudesucks"
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

    public static void main(String[] args) {
        Connection connection = connectToDB();
        
        if (connection != null) {
            // Fetch all subscribers
            ArrayList<Subscriber> subscribers = getAllSubscribers(connection);
            
            if (!subscribers.isEmpty()) {
                
                Subscriber subscriber = subscribers.get(0); // Get the first subscriber (modify the index as needed)
                System.out.println("Subscriber before update: " + subscriber);
                
                // Update subscriber information
                boolean success = updateSubscriber(connection, subscriber.getSubscriberId(), "newemail@example.com", "123-456-7890");
                
                if (success) {
                    System.out.println("Updated Subscriber: " + subscriber);
                } else {
                    System.out.println("Failed to update subscriber information.");
                }
            } else {
                System.out.println("No subscribers found.");
            }
        } else {
            System.out.println("Failed to establish database connection.");
        }
    }

}
