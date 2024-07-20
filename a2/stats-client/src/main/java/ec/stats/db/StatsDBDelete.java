package ec.stats.db;

import java.sql.*;

public class StatsDBDelete {

    // JDBC URL, username, and password of MySQL server
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/stats_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        if (args.length < 2 || !args[0].equals("-table")) {
            System.out.println("Usage: java StatsDBDelete -table <table_name> <additional_arguments>");
            return;
        }

        String tableName = args[1];
        String[] additionalArgs = new String[args.length - 2];
        System.arraycopy(args, 2, additionalArgs, 0, args.length - 2);

        try {
            // Connect to MySQL database
        	Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Delete record from the specified table
            if (tableName.equals("ecuser")) {
                deleteFromEcUser(connection, additionalArgs);
            } else if (tableName.equals("ecmodel")) {
                deleteFromEcModel(connection, additionalArgs);
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

    private static void deleteFromEcUser(Connection connection, String[] args) throws SQLException {
        if (args.length != 2 || !args[0].equals("-name")) {
            System.out.println("Usage: java StatsDBDelete -table ecuser -name <name>");
            return;
        }

        String name = args[1];

        // SQL query to delete record from ecuser table
        String deleteSQL = "DELETE FROM ecuser WHERE name = ?";

        // Execute SQL query
        PreparedStatement statement = connection.prepareStatement(deleteSQL);
        statement.setString(1, name);
        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Deleted " + rowsAffected + " record(s) from ecuser table for user: " + name);
        } else {
            System.out.println("No records found to delete for user: " + name);
        }
    }

    private static void deleteFromEcModel(Connection connection, String[] args) throws SQLException {
    	if (args.length != 2 || !args[0].equals("-name")) {
            System.out.println("Usage: java StatsDBDelete -table ecmodel -name <name>");
            return;
        }

        String name = args[1];

        // SQL query to delete record from ecmodel table
        String deleteSQL = "DELETE FROM ecmodel WHERE name = ?";

        // Execute SQL query
        PreparedStatement statement = connection.prepareStatement(deleteSQL);
        statement.setString(1, name);
        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Deleted " + rowsAffected + " record(s) from ecmodel table for model: " + name);
        } else {
            System.out.println("No records found to delete for model: " + name);
        }
    }
}
