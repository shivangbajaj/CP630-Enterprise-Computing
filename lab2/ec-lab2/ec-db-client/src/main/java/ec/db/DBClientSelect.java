package ec.db;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ec.stats.StatsSummary;

public class DBClientSelect {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/test";
	static final String USER = "root";
	static final String PASS = "";

	public static void main(String[] args) throws Exception {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String sql;

		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			statement = connection.createStatement();

			sql = "SELECT * FROM ecuser";
			resultSet = statement.executeQuery(sql);
			if (resultSet != null) {
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					System.out.print("ID: " + id);

					String name = resultSet.getString("name");
					System.out.print("\nNAME: " + name);

					String password = resultSet.getString("password");
					System.out.print("\nPASSWORD: " + password);

					int role = resultSet.getInt("role");
					System.out.print("\nROLE: " + role + "\n");

				}
			}

			sql = "SELECT * FROM model";
			resultSet = statement.executeQuery(sql);

			if (resultSet != null) {

				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					System.out.print("ID: " + id);

					String name = resultSet.getString("name");
					System.out.print("\nNAME: " + name);

					if (name.equals("stats")) {

						byte[] buf = resultSet.getBytes("object");

						if (buf != null) {

							ObjectInputStream objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));

							StatsSummary statsmodel = (StatsSummary) objectIn.readObject();

							System.out.print("\nCOUNT: " + statsmodel.getCount());
						}
					}

					String classname = resultSet.getString("classname");
					System.out.print("\nMODELCLASS: " + classname);

					String datetime = resultSet.getString("date");
					System.out.print("\nTIME: " + datetime);

				}
			}

		} catch (SQLException e) { // Handle errors for JDBC
			e.printStackTrace();
		} catch (Exception e) { // Handle errors for Class.forName
			e.printStackTrace();
		} finally { // finally block used to close resources
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

}