package by.it.dao;

import java.io.Serializable;
import java.sql.SQLException;

/**
 * Created by Evgeni on 09.08.17.
 */

public interface DAO<T> {
    T save(T t) throws SQLException;

    T get(Serializable id) throws SQLException;

    void update(T t) throws SQLException;

    int delete(Serializable id) throws SQLException;
}

