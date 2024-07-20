package ec.stats.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class StatsDBDrop {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DBUtil.URL, DBUtil.USER, DBUtil.PASS);
            
            // Drop ecuser table
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS ecuser");
            
            // Drop ecmodel table
            statement.executeUpdate("DROP TABLE IF EXISTS ecmodel");
            
            System.out.println("Tables dropped successfully.");
            
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
