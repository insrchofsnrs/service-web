package com.systp.services.impl;

import by.it.bd.DBConnectManager;
import by.it.bd.DBExceptionManager;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Smile on 27.08.2017.
 */
public abstract class AbstractService {
    public void startTransaction() throws SQLException {
        DBConnectManager.getConnection().setAutoCommit(false);
    }

    public void commit() throws SQLException {
        DBConnectManager.getConnection().commit();
    }

    public Connection getConnection() {
        return DBConnectManager.getConnection();
    }

    public void rollback() {
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            throw new DBExceptionManager("rollback error");
        }
    }
}
