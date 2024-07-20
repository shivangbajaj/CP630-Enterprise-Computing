package ec.db;

import java.sql.*;

import ec.stats.StatsSummary;

import java.io.*;

public class DBClientInsert {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/test";
	static final String USER = "root";
	static final String PASS = "";

	public static void write(Object obj, PreparedStatement ps, int parameterIndex) throws SQLException, IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(obj);
		oos.close();
		ps.setBytes(parameterIndex, baos.toByteArray());
	}

	public static Object read(ResultSet rs, String columnname)
			throws SQLException, IOException, ClassNotFoundException {
		byte[] buffer = rs.getBytes(columnname);
		if (buffer != null) {
			ObjectInputStream objectIn = new ObjectInputStream(new ByteArrayInputStream(buffer));
			return objectIn.readObject();
		}
		return null;
	}

	public static void main(String[] args) throws Exception {

		Connection connection = null;
		Statement statement = null;
		ResultSet rs;
		String sql;

		try {

			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			statement = connection.createStatement();

			sql = "Delete from ecuser where ecuser.name = 'admin'";
			statement.execute(sql);

			sql = "INSERT INTO ecuser (name, password, role) VALUES ('admin', 'cp630', 1)";
			statement.execute(sql);

			sql = "Delete from model where model.name = 'stats'";
			statement.execute(sql);

			sql = "INSERT INTO MODEL (name, classname, object) VALUES ('stats', ?, ?)";
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);
			StatsSummary ssobj = new StatsSummary();
			ssobj.setCount(1);
			ssobj.setMin(1);
			ssobj.setMax(1);
			ssobj.setMean(1);
			ssobj.setSTD(0);

			// set input parameters
			ps.setString(1, ssobj.getClass().getName());
			ps.setObject(2, ssobj);

			ps.executeUpdate();

			ps.close();

			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT object FROM model");

			while (rs.next()) {
				System.out.println(read(rs, "object").toString());
			}

			rs.close();
			statement.close();

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