package ec.stats.db;

import java.sql.*;

public class StatsDBDelete {
    public static void main(String[] args) {
        if (args.length < 2 || !args[0].equals("-table")) {
            System.out.println("Usage: java -cp target/stats-client.jar ec.stats.db.StatsDBDelete -table <table_name> [options]");
            return;
        }

        String tableName = args[1];

		try (Connection connection = DriverManager.getConnection(DBUtil.URL, DBUtil.USER, DBUtil.PASS)) {
            if (tableName.equalsIgnoreCase("ecuser")) {
                if (args.length != 4 || !args[2].equals("-name")) {
                    System.out.println("Usage: java -cp target/stats-client.jar ec.stats.db.StatsDBDelete -table ecuser -name <name>");
                    return;
                }

                String name = args[3];

                try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ecuser WHERE name = ?")) {
                    preparedStatement.setString(1, name);
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Record with name '" + name + "' deleted successfully from ecuser table.");
                    } else {
                        System.out.println("No record found for name: " + name);
                    }
                }
            } else if (tableName.equalsIgnoreCase("ecmodel")) {
            	 if (args.length != 4 || !args[2].equals("-name")) {
                     System.out.println("Usage: java -cp target/stats-client.jar ec.stats.db.StatsDBDelete -table ecuser -name <name>");
                     return;
                 }

                 String name = args[3];

                 try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ecmodel WHERE name = ?")) {
                     preparedStatement.setString(1, name);
                     int rowsAffected = preparedStatement.executeUpdate();

                     if (rowsAffected > 0) {
                         System.out.println("Record with name '" + name + "' deleted successfully from ecmodel table.");
                     } else {
                         System.out.println("No record found for name: " + name);
                     }
                 }
            } else {
                System.out.println("Invalid table name: " + tableName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
