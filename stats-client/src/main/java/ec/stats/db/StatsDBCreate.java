package ec.stats.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class StatsDBCreate {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DBUtil.URL, DBUtil.USER, DBUtil.PASS);
            
            // Create ecuser table
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS ecuser (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), password VARCHAR(255), role INT)");
            
            // Create ecmodel table
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS ecmodel (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), object MEDIUMBLOB, classname VARCHAR(255), date TIMESTAMP)");
            
            System.out.println("Tables created successfully.");
            
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
