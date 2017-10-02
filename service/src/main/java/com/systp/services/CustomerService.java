package com.systp.services;



import java.io.Serializable;
import java.util.List;

/**
 * Created by Evgeni on 25.08.17.
 */
public interface CustomerService {
    Customer createCustomer (Customer customer);
    Customer get(Serializable id);
    void update(Customer order);
    int delete(Serializable id);

    List<Customer> getAllByDate ();
    List<Customer> getAll ();
}
