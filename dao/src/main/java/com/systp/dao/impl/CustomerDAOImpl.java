package com.systp.dao.impl;

import by.it.dao.CustomerDAO;
import by.it.entities.Customer;
import com.systp.dao.impl.AbstractDao;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeni on 25.08.17.
 */
public class CustomerDAOImpl extends AbstractDao implements CustomerDAO {

    private static volatile CustomerDAO INSTANCE = null;

    private static final String saveCustomerQuery = "INSERT INTO CUSTOMER (organization, first_name, second_name, position, phone_number_a, phone_number_b, phone_number_fax, email) VALUES (?, ?, ?, ?, ?, ?,?,?)";
    private static final String updateCustomerQuery = "UPDATE ITEM SET QUANTITY=?, DISCOUNT=? WHERE ITEM_ID=?";
    private static final String getCustomerQuery = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID=?";
    private static final String getCustomerByItemQuery = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID = ?";
    private static final String getAllCustomerQuery = "SELECT * FROM CUSTOMER";
    private static final String deleteCustomerQuery = "DELETE FROM ITEM WHERE ITEM_ID=?";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetAllByItemId;
    private PreparedStatement psGetAll;
    private PreparedStatement psDelete;


    @Override
    public Customer save(Customer customer) throws SQLException {
        psSave = prepareStatement(saveCustomerQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setString(1, customer.getOrganization());
        psSave.setString(2, customer.getFirstName());
        psSave.setString(3, customer.getSecondName());
        psSave.setString(4, customer.getPosition());
        psSave.setString(5, customer.getPhoneNumberA());
        psSave.setString(6, customer.getPhoneNumberB());
        psSave.setString(7, customer.getPhoneNumberFax());
        psSave.setString(8, customer.getEmail());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            customer.setId(rs.getLong(1));
        }
        close(rs);
        return customer;
    }

    @Override
    public Customer get(Serializable id) throws SQLException {
        psGet = prepareStatement(getCustomerQuery);
        psGet.setLong(1, (long)id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return populateEntity(rs);
        }
        close(rs);
        return null;
    }
    private Customer populateEntity(ResultSet rs) throws SQLException {
        Customer entity = new Customer();
        entity.setId(rs.getLong(1));
        entity.setOrganization(rs.getString(2));
        entity.setFirstName(rs.getString(3));
        entity.setSecondName(rs.getString(4));
        entity.setPosition(rs.getString(5));
        entity.setPhoneNumberA(rs.getString(6));
        entity.setPhoneNumberB(rs.getString(7));
        entity.setPhoneNumberFax(rs.getString(8));
        entity.setEmail(rs.getString(9));

        return entity;
    }
    @Override
    public void update(Customer customer) throws SQLException {

    }

    @Override
    public int delete(Serializable id) throws SQLException {
        return 0;
    }

    @Override
    public Customer getByItemId(long itemId) throws SQLException {
        psGetAllByItemId = prepareStatement(getCustomerByItemQuery);
        psGetAllByItemId.setLong(1, itemId);
        psGetAllByItemId.execute();
        ResultSet rs = psGetAllByItemId.getResultSet();

        if (rs.next()) {
            populateEntity(rs);
        }
        close(rs);

        return populateEntity(rs);
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        psGetAll = prepareStatement(getAllCustomerQuery);
        psGetAll.execute();
        ResultSet rs = psGetAll.getResultSet();
        List<Customer> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateEntity(rs));
        }
        close(rs);
        return list;
    }

    public static CustomerDAO getInstance() {
        CustomerDAO itemDao = INSTANCE;
        if (itemDao == null) {
            synchronized (ItemDAOImpl.class) {
                itemDao = INSTANCE;
                if (itemDao == null) {
                    INSTANCE = itemDao = new CustomerDAOImpl();
                }
            }
        }
        return itemDao;
    }
}
