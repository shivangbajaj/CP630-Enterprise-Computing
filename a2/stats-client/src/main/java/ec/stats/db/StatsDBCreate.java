package ec.stats.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StatsDBCreate {

    // JDBC URL, username, and password of MySQL server
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/stats_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try {
            // Connect to MySQL database
        	Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // SQL statements to create tables
            String createUserTableSQL = "CREATE TABLE ecuser (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), password VARCHAR(255), role INT)";
            String createModelTableSQL = "CREATE TABLE ecmodel (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), object MEDIUMBLOB, classname VARCHAR(255), date TIMESTAMP)";

            // Create tables
            Statement statement = connection.createStatement();
            statement.executeUpdate(createUserTableSQL);
            statement.executeUpdate(createModelTableSQL);
            
            System.out.println("Tables created successfully.");

            // Close resources
            statement.close();
            connection.close();

        } catch (SQLException e) { // Handle errors for JDBC
			e.printStackTrace();
		} catch (Exception e) { // Handle errors for Class.forName
			e.printStackTrace();
		}
    }
}
