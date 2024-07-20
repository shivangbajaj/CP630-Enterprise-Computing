package ec.stats.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StatsDBDrop {

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

            // SQL statements to drop tables
            String dropUserTableSQL = "DROP TABLE IF EXISTS ecuser";
            String dropModelTableSQL = "DROP TABLE IF EXISTS ecmodel";

            // Drop tables
            Statement statement = connection.createStatement();
            statement.executeUpdate(dropUserTableSQL);
            statement.executeUpdate(dropModelTableSQL);
            
            System.out.println("Tables dropped successfully.");

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
