package ec.stats.db;

import java.sql.*;

public class StatsDBSelect {

    // JDBC URL, username, and password of MySQL server
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/stats_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        if (args.length < 2 || !args[0].equals("-table")) {
            System.out.println("Usage: java StatsDBSelect -table <table_name> <additional_arguments>");
            return;
        }

        String tableName = args[1];
        String[] additionalArgs = new String[args.length - 2];
        System.arraycopy(args, 2, additionalArgs, 0, args.length - 2);

        try {
            // Connect to MySQL database
        	Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Retrieve record information from the specified table
            if (tableName.equals("ecuser")) {
                selectFromEcUser(connection, additionalArgs);
            } else if (tableName.equals("ecmodel")) {
                selectFromEcModel(connection, additionalArgs);
            } else {
                System.out.println("Invalid table name.");
            }

            // Close connection
            connection.close();

        } catch (SQLException e) { // Handle errors for JDBC
			e.printStackTrace();
		} catch (Exception e) { // Handle errors for Class.forName
			e.printStackTrace();
		}
    }

    private static void selectFromEcUser(Connection connection, String[] args) throws SQLException {
        if (args.length != 2 || !args[0].equals("-name")) {
            System.out.println("Usage: java StatsDBSelect -table ecuser -name <name>");
            return;
        }

        String name = args[1];

        // SQL query to retrieve record from ecuser table
        String selectSQL = "SELECT * FROM ecuser WHERE name = ?";

        // Execute SQL query
        PreparedStatement statement = connection.prepareStatement(selectSQL);
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();

        // Print record information
        if (resultSet.next()) {
            System.out.println("User ID: " + resultSet.getInt("id"));
            System.out.println("Name: " + resultSet.getString("name"));
            System.out.println("Password: " + resultSet.getString("password"));
            System.out.println("Role: " + resultSet.getInt("role"));
        } else {
            System.out.println("No user found with name: " + name);
        }
    }

    private static void selectFromEcModel(Connection connection, String[] args) throws SQLException {
        // Add your implementation to select records from the ecmodel table based on the provided command-line arguments
        // This method is left empty as an exercise
    }
}
