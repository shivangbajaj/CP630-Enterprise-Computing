package ec.stats.db;

import java.sql.*;

public class StatsDBSelect {
	public static void main(String[] args) {
		if (args.length < 2 || !args[0].equals("-table")) {
			System.out.println("Usage: java -cp target/stats-client.jar ec.stats.db.StatsDBSelect -table <table_name> [options]");
			return;
		}

		String tableName = args[1];

		try (Connection connection = DriverManager.getConnection(DBUtil.URL, DBUtil.USER, DBUtil.PASS)) {
			if (tableName.equalsIgnoreCase("ecuser")) {

				if (args.length != 4 || !args[2].equals("-name")) {
					System.out.println("Usage: java -cp target/stats-client.jar ec.stats.db.StatsDBSelect -table ecuser -name <name>");
					return;
				}

				String name = args[3];

				try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ecuser WHERE name = ?")) {
					preparedStatement.setString(1, name);
					ResultSet resultSet = preparedStatement.executeQuery();

					while (resultSet.next()) {
						long id = resultSet.getLong("id");
						String userName = resultSet.getString("name");
						String password = resultSet.getString("password");
						int role = resultSet.getInt("role");
						System.out.println("ID: " + id + ", Name: " + userName + ", Password: " + password + ", Role: " + role);
					}
				}
			} else if (tableName.equalsIgnoreCase("ecmodel")) {

				if (args.length != 4 || !args[2].equals("-name")) {
					System.out.println("Usage: java -cp target/stats-client.jar ec.stats.db.StatsDBSelect -table ecuser -name <name>");
					return;
				}
				
				String name = args[3];

				try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ecmodel WHERE name = ?")) {
					preparedStatement.setString(1, name);
					ResultSet resultSet = preparedStatement.executeQuery();

					while (resultSet.next()) {
						long id = resultSet.getLong("id");
						String modelName = resultSet.getString("name");
						byte[] bytes = resultSet.getBytes("object");
						String className = resultSet.getString("classname");
						Timestamp  timestamp = resultSet.getTimestamp("date");
                        StringBuilder hexString = new StringBuilder();
						for (byte b : bytes) {
                            hexString.append(String.format("%02X ", b));
                        }
						System.out.println("ID: " + id + ", Name: " + modelName + ", object: " + hexString.toString() + ", Class Name: " + className + ", Time Stamp: " + timestamp);
					}
				}
			} else {
				System.out.println("Invalid table name: " + tableName);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
