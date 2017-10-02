package com.systp.dao;



import com.systp.pojos.User;

import java.sql.SQLException;

/**
 * Created by Evgeni on 25.08.17.
 */
public interface UserDAO extends DAO<User> {
    User getByLogin(String login) throws SQLException;
}
