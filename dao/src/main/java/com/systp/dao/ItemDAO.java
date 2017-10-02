package com.systp.dao;



import com.systp.pojos.Item;

import java.sql.SQLException;
import java.util.List;


public interface ItemDAO extends DAO<Item>{
    List<Item> getByCustomerId(long customerId) throws SQLException;
    List<Item> getAll() throws SQLException;
}
