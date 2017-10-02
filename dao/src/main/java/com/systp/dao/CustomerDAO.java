package com.systp.dao;



import com.systp.pojos.Customer;

import java.sql.SQLException;
import java.util.List;


public interface CustomerDAO extends DAO<Customer> {
    Customer  getByItemId (long itemId) throws SQLException;
    List<Customer> getAll () throws  SQLException;

}
