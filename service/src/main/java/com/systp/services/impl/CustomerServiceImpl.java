package by.it.services.impl;


import by.it.dao.CustomerDAO;
import by.it.dao.ItemDAO;
import by.it.dao.UserDAO;
import by.it.dao.impl.AbstractDao;
import by.it.dao.impl.CustomerDAOImpl;
import by.it.dao.impl.ItemDAOImpl;
import by.it.dao.impl.UserDAOImpl;
import by.it.entities.Customer;
import by.it.services.CustomerService;
import by.it.services.ServiceException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;




public class CustomerServiceImpl extends AbstractService implements CustomerService {

    private static volatile CustomerService INSTANCE = null;
    private CustomerDAO customerDAO = CustomerDAOImpl.getInstance();


    public static CustomerService getInstance() {
        CustomerService customerService = INSTANCE;
        if (customerService == null) {
            synchronized (CustomerServiceImpl.class) {
                customerService = INSTANCE;
                if (customerService == null) {
                    INSTANCE = customerService = new CustomerServiceImpl();
                }
            }
        }
        return customerService;
    }


    @Override
    public Customer createCustomer(Customer customer) {
        try {
            customerDAO.save(customer);
        } catch (SQLException e){
            throw new ServiceException("Ошибка создания заказчика " + customer, e);
        }
        return customer;
    }

    @Override
    public Customer get(Serializable id) {
        try{
            return customerDAO.get(id);
        } catch (SQLException e){
            throw new ServiceException("Error getting Customer by id " + id);
        }
    }

    @Override
    public void update(Customer order) {

    }

    @Override
    public int delete(Serializable id) {
        return 0;
    }

    @Override
    public List<Customer> getAllByDate() {
        return null;
    }

    @Override
    public List<Customer> getAll() {

        try {
            startTransaction();
            List<Customer> list = customerDAO.getAll();
            commit();
            return list;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting Products");
        }
    }
}
