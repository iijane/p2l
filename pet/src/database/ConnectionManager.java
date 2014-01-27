package database;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;

/**
 * A class that manages connections to the database. It also
 * has a utility method that close connections, statements and
 * resultsets
 */
public class ConnectionManager {
    private static String JDBC_DRIVER = "jdbc.driver";
    private static String JDBC_URL = "jdbc.url";
    private static String JDBC_USER = "jdbc.user";
    private static String JDBC_PASSWORD = "jdbc.password";
    private static Properties props = new Properties();

    static {
        try {
            InputStream is = ConnectionManager.class.getResourceAsStream("connection.properties");
            props.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class.forName(props.getProperty(JDBC_DRIVER)).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a connection to the database
     *
     * @return the connection
     * @throws SQLException if an error occurs when connecting
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(props.getProperty(JDBC_URL),
                props.getProperty(JDBC_USER),
                props.getProperty(JDBC_PASSWORD));
    }

    /**
     * close the given connection, statement and resultset
     *
     * @param conn the connection object to be closed
     * @param stmt the statement object to be closed
     * @param rs   the resultset object to be closed
     */
    public static void close(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(ConnectionManager.getConnection());

    }
}
