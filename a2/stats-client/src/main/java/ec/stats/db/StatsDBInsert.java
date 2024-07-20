package ec.stats.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class StatsDBInsert {

    // JDBC URL, username, and password of MySQL server
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/stats_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java StatsDBInsert -table <table_name> <additional_arguments>");
            return;
        }

        String tableName = null;
        List<String> additionalArgs = new ArrayList<String>(); // Specify generic type

        // Parse command-line arguments
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-table") && i + 1 < args.length) {
                tableName = args[i + 1];
            } else {
                additionalArgs.add(args[i]);
            }
        }

        if (tableName == null) {
            System.out.println("Please provide the table name using the -table option.");
            return;
        }

        try {
            // Connect to MySQL database
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Insert record into the specified table
            if (tableName.equals("ecuser")) {
                insertIntoEcUser(connection, additionalArgs);
            } else if (tableName.equals("ecmodel")) {
                insertIntoEcModel(connection, additionalArgs);
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

    private static void insertIntoEcUser(Connection connection, List<String> argsl) throws SQLException {
        if (argsl.size() != 7) {
            System.out.println("Usage: java StatsDBInsert -table ecuser -name <name> -password <password> -role <role>");
            return;
        }

        String name = argsl.get(2);
        String password = argsl.get(4);
        int role = Integer.parseInt(argsl.get(6));

        // SQL statement to insert record into ecuser table
        String insertSQL = "INSERT INTO ecuser (name, password, role) VALUES (?, ?, ?)";

        // Execute SQL statement
        PreparedStatement statement = connection.prepareStatement(insertSQL);
        statement.setString(1, name);
        statement.setString(2, password);
        statement.setInt(3, role);
        statement.executeUpdate();

        System.out.println("Record inserted into ecuser table successfully.");
    }

    private static void insertIntoEcModel(Connection connection, List<String> args) throws SQLException, IOException {
        if (args.size() != 5 || !args.get(1).equals("-name") || !args.get(3).equals("-modelfile")) {
            System.out.println("Usage: java StatsDBInsert -table ecmodel -name <name> -modelfile <file_path>");
            return;
        }

        String name = args.get(2);
        String modelFilePath = args.get(4);

        // Read model file content
        byte[] modelContent = Files.readAllBytes(Paths.get(modelFilePath));

        // SQL statement to insert record into ecmodel table
        String insertSQL = "INSERT INTO ecmodel (name, object, classname, date) VALUES (?, ?, ?, ?)";

        // Execute SQL statement
        PreparedStatement statement = connection.prepareStatement(insertSQL);
        statement.setString(1, name);
        statement.setBytes(2, modelContent);
        statement.setString(3, "StatsSummary"); // Assuming object class name is StatsSummary
        statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
        statement.executeUpdate();

        System.out.println("Record inserted into ecmodel table successfully.");
    }
}
