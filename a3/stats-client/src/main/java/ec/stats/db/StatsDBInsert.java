package ec.stats.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatsDBInsert {
    public static void main(String[] args) {
        if (args.length < 2 || !args[0].equals("-table")) {
            System.out.println("Usage: java -cp target/stats-client.jar ec.stats.db.StatsDBInsert -table <table_name> [options]");
            return;
        }

        String tableName = args[1];

        try (Connection connection = DriverManager.getConnection(DBUtil.URL, DBUtil.USER, DBUtil.PASS)) {
            if (tableName.equalsIgnoreCase("ecuser")) {
                if (args.length != 8 || !args[2].equals("-name") || !args[4].equals("-password") || !args[6].equals("-role")) {
                    System.out.println("Usage: java -cp target/stats-client.jar ec.stats.db.StatsDBInsert -table ecuser -name <name> -password <password> -role <role>");
                    return;
                }

                String name = args[3];
                String password = args[5];
                int role = Integer.parseInt(args[7]);

                try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ecuser (name, password, role) VALUES (?, ?, ?)")) {
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, password);
                    preparedStatement.setInt(3, role);
                    preparedStatement.executeUpdate();
                }
            } else if (tableName.equalsIgnoreCase("ecmodel")) {
                if (args.length != 6 || !args[2].equals("-name") || !args[4].equals("-modelfile")) {
                    System.out.println("Usage: java -cp target/stats-client.jar ec.stats.db.StatsDBInsert -table ecmodel -name <name> -modelfile <file_path>");
                    return;
                }

                String name = args[3];
                String filePath = args[5];

                File file = new File(filePath);
                byte[] modelBytes = new byte[(int) file.length()];
                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    fileInputStream.read(modelBytes);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }

                try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ecmodel (name, object, classname, date) VALUES (?, ?, ?, NOW())")) {
                    preparedStatement.setString(1, name);
                    preparedStatement.setBytes(2, modelBytes);
                    if (name.contains("weka")) {
                        preparedStatement.setString(3, "LinearRegression");
                    } else {
                        preparedStatement.setString(3, "StatsSummary");
                    }
                    preparedStatement.executeUpdate();
                }
            } else {
                System.out.println("Invalid table name: " + tableName);
                return;
            }

            System.out.println("Record inserted successfully into table: " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
