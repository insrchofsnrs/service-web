package by.it.dao;

import by.it.entities.User;

import java.sql.SQLException;

/**
 * Created by Evgeni on 25.08.17.
 */
public interface UserDAO extends DAO<User> {
    User getByLogin(String login) throws SQLException;
}
