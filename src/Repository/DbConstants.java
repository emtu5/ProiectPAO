package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConstants {
    public static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/eurovision";
    public static final String DB_USER = "paul";
    public static final String DB_PASSWORD = "paoeurovision";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
    }
}
