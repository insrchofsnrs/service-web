package com.systp.dao.impl;

import by.it.bd.DBConnectManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Smile on 18.08.2017.
 */
public abstract class AbstractDao {

    protected PreparedStatement prepareStatement(String query) throws SQLException {
        return DBConnectManager.getConnection().prepareStatement(query);
    }

    protected PreparedStatement prepareStatement(String query, int flag) throws SQLException {
        return DBConnectManager.getConnection().prepareStatement(query, flag);
    }

    protected void close(ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
