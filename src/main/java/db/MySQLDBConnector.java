package db;

import config.DBConfigurator;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class MySQLDBConnector implements IDBConnector{
    private DBConfigurator dbConfigurator = new DBConfigurator();
    private Connection connection = null;
    private Statement statement;

    public MySQLDBConnector() {
        connect();
    }

    private void connect() {
        try {
            Properties configuration = dbConfigurator.getDBConfig();
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(
                        String.format("jdbc:mysql://%s:%s@%s/%s",
                                configuration.getProperty("username"),
                                configuration.getProperty("password"),
                                configuration.getProperty("db_url"),
                                configuration.getProperty("db_name")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean execute(String sql) throws SQLException {
        try (Statement statement = getConnection().createStatement()) {
            return statement.execute(sql);
        }
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        Statement statement = getConnection().createStatement();
        return statement.executeQuery(sql);
    }

    @Override
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Override
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connect();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
