package com.systp.bd;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.sql.Connection;

/**
 * Created by Smile on 18.08.2017.
 */
public class DataSource {

    private static DataSource dataSource;
    private ComboPooledDataSource pooledDataSource;

    private final String URL;
    private final String USER;
    private final String PASSWORD;
    private final String DRIVER;


     {
        ResourceBundle rb = ResourceBundle.getBundle("db");
        if (rb == null) {
            URL = "UNDEFINED";
            USER = "UNDEFINED";
            PASSWORD = "UNDEFINED";
            DRIVER = "com.mysql.jdbc.Driver";
            System.out.println("Бандл для базы данных не был инициализирован");
        } else {
            URL = rb.getString("url");
            USER = rb.getString("user");
            PASSWORD = rb.getString("password");
            DRIVER = rb.getString("driver");
        }
    }

    private DataSource() throws IOException, SQLException, PropertyVetoException {
        pooledDataSource = new ComboPooledDataSource();
        pooledDataSource.setDriverClass(DRIVER); //loads the jdbc driver
        pooledDataSource.setJdbcUrl(URL);
        pooledDataSource.setUser(USER);
        pooledDataSource.setPassword(PASSWORD);

        // the settings below are optional -- c3p0 can work with defaults
        pooledDataSource.setMinPoolSize(10);
        pooledDataSource.setAcquireIncrement(5);
        pooledDataSource.setMaxPoolSize(20);
        pooledDataSource.setMaxStatements(180);
    }

    public static synchronized DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (dataSource == null) {
            dataSource = new DataSource();
            return dataSource;
        } else {
            return dataSource;
        }
    }

    public Connection getConnection() throws SQLException {
        return pooledDataSource.getConnection();
    }

}
