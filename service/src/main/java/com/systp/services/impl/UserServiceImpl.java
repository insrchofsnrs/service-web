package com.systp.services.impl;

import by.it.dao.UserDAO;
import by.it.dao.impl.UserDAOImpl;
import by.it.entities.User;
import by.it.services.ServiceException;
import by.it.services.UserService;
import com.systp.services.impl.AbstractService;

import java.sql.SQLException;

/**
 * Created by Smile on 27.08.2017.
 */
public class UserServiceImpl extends AbstractService implements UserService {

    private static volatile UserService INSTANCE = null;
    private UserDAO userDao = UserDAOImpl.getInstance();



    @Override
    public User getByLogin(String login) {
        try {
            return userDao.getByLogin(login);
        } catch (SQLException e) {
            throw new ServiceException("Error getting User by login" + login);
        }
    }

    public static UserService getInstance() {
        UserService userService = INSTANCE;
        if (userService == null) {
            synchronized (UserServiceImpl.class) {
                userService = INSTANCE;
                if (userService == null) {
                    INSTANCE = userService = new UserServiceImpl();
                }
            }
        }

        return userService;
    }
}
