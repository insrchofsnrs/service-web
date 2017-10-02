package by.it.dao.impl;

import by.it.bd.DBConnectManager;
import by.it.bd.DBExceptionManager;
import by.it.dao.ItemDAO;
import by.it.entities.Item;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Evgeni on 09.08.17.
 */
public class ItemDAOImpl extends AbstractDao implements ItemDAO {

    private static volatile ItemDAO INSTANCE = null;

    private static final String saveItemQuery = "INSERT INTO ITEM (label, name, marking, item_price, customer_id, status, date) VALUES (?, ?, ?, ?, ?, ?, now())";
    private static final String updateItemQuery = "UPDATE ITEM SET QUANTITY=?, DISCOUNT=? WHERE ITEM_ID=?";
    private static final String getItemQuery = "SELECT * FROM ITEM WHERE ITEM_ID=?";
    private static final String getAll = "SELECT * FROM ITEM";
    private static final String getItemsByCustomerQuery = "SELECT * FROM ITEM WHERE CUSTOMER_ID = ?";
    private static final String deleteItemQuery = "DELETE FROM ITEM WHERE ITEM_ID=?";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetAll;
    private PreparedStatement psGetAllItem;
    private PreparedStatement psDelete;

    private ItemDAOImpl() {
    }
    public static ItemDAO getInstance() {
        ItemDAO itemDao = INSTANCE;
        if (itemDao == null) {
            synchronized (ItemDAOImpl.class) {
                itemDao = INSTANCE;
                if (itemDao == null) {
                    INSTANCE = itemDao = new ItemDAOImpl();
                }
            }
        }
        return itemDao;
    }

    @Override
    public Item save(Item it) throws SQLException {
        Item item = it;
        psSave = prepareStatement(saveItemQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setString(1, item.getLabel());
        psSave.setString(2, item.getName());
        psSave.setString(3, item.getMarking());
        psSave.setInt(5, (int)item.getCustomerId());
        psSave.setDouble(4, item.getItemPrice());
        psSave.setString(6, item.getStatus());
       // psSave.setDate(7, item.getDataTime());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            item.setId(rs.getLong(1));
        }
        close(rs);
        return item;
    }

    @Override
    public Item get(Serializable id) throws SQLException {
        psGet = prepareStatement(getItemQuery);
        psGet.setLong(1, (int)id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return populateEntity(rs);
        }
        close(rs);
        return null;
    }

    private Item populateEntity(ResultSet rs) throws SQLException {
        Item entity = new Item();
        entity.setId(rs.getLong(1));
        entity.setLabel(rs.getString(2));
      // entity.setDataTime(rs.getDate(3));
        entity.setName(rs.getString(4));
        entity.setItemPrice(rs.getDouble(5));
        entity.setMarking(rs.getString(6));
        entity.setCustomerId(rs.getLong(7));
        entity.setStatus(rs.getString(8));
        return entity;
    }

    @Override
    public List<Item> getByCustomerId(long customerId) throws SQLException {
        psGetAll = prepareStatement(getItemsByCustomerQuery);
        psGetAll.setLong(1, customerId);
        psGetAll.execute();
        List<Item> list = new ArrayList<>();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            list.add(populateEntity(rs));
        }
        close(rs);
        return list;
    }

    @Override
    public List<Item> getAll() throws SQLException {
        psGetAllItem = prepareStatement(getAll);
        psGetAllItem.execute();
        List<Item> list = new ArrayList<>();
        ResultSet rs = psGetAllItem.getResultSet();
        while (rs.next()){
            list.add(populateEntity(rs));
        }
        close(rs);
        return list;
    }

    @Override
    public void update(Item item) throws SQLException {

    }

    @Override
    public int delete(Serializable id) throws SQLException {
        return 0;
    }
}
