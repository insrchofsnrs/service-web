package com.systp.dao.impl;
import by.it.dao.UserDAO;
import by.it.entities.User;
import com.systp.dao.impl.AbstractDao;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by Evgeni on 25.08.17.
 */
public class UserDAOImpl extends AbstractDao implements UserDAO {

    private static volatile UserDAO INSTANCE = null;

    private static final String getUser = "SELECT * FROM USER WHERE LOGIN=?";
    private PreparedStatement psGetByLogin;

    public static UserDAO getInstance() {
        UserDAO userDao = INSTANCE;
        if (userDao == null) {
            synchronized (UserDAOImpl.class) {
                userDao = INSTANCE;
                if (userDao == null) {
                    INSTANCE = userDao = new UserDAOImpl();
                }
            }
        }
        return userDao;
    }

    @Override
    public User save(User user) throws SQLException {
        return null;
    }

    @Override
    public User get(Serializable id) throws SQLException {
        return null;
    }

    @Override
    public void update(User user) throws SQLException {

    }

    @Override
    public int delete(Serializable id) throws SQLException {
        return 0;
    }

    @Override
    public User getByLogin(String login) throws SQLException {
        psGetByLogin = prepareStatement(getUser);
        psGetByLogin.setString(1, login);
        ResultSet rs = psGetByLogin.executeQuery();
        if (rs.next()) {
            return populateEntity(rs);
        }
        close(rs);
        return null;
    }

    private User populateEntity(ResultSet rs) throws SQLException {
        User entity = new User();
        entity.setId(rs.getLong(1));
        entity.setFirstName(rs.getString(2));
        entity.setSecondName(rs.getString(3));
        entity.setLogin(rs.getString(4));
        entity.setPassword(rs.getString(5));
        entity.setPosition(rs.getString(6));
        return entity;
    }
}
