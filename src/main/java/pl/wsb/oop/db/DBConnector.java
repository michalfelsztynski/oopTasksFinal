package pl.wsb.oop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnector {
    static private Connection CONNECTION;

    public static void connect() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", JDBCConnector.USER);
        props.setProperty("password", JDBCConnector.PASS);
        CONNECTION = DriverManager.getConnection(JDBCConnector.DB_URL, props);
        System.out.println("connected");
    }

    public static Statement getStatement() throws SQLException {
        return CONNECTION.createStatement();
    }

    public static void executeSQL(String sql) throws SQLException {
        CONNECTION.createStatement().execute(sql);
    }
}
