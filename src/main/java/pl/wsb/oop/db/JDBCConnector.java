package pl.wsb.oop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCConnector {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/testDBoop";
    static final String USER = "postgres";
    static final String PASS = "";

    public static void connect() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASS);

        Connection conn = DriverManager.getConnection(DB_URL, props);
        System.out.println(conn.getClientInfo()); // simple check

        Statement sttm = conn.createStatement();

        String sql = "INSERT into animal VALUES ('some2',rand())";
        sttm.executeUpdate(sql);
    }

}
