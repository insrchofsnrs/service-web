package com.systp.services.impl;

import by.it.dao.ItemDAO;
import by.it.dao.impl.ItemDAOImpl;
import by.it.entities.Item;
import by.it.services.ItemService;
import by.it.services.ServiceException;
import com.systp.services.impl.AbstractService;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Evgeni on 31.08.17.
 */
public class ItemServiceImpl extends AbstractService implements ItemService {
    private static volatile ItemService INSTANCE = null;
    ItemDAO itemDAO = ItemDAOImpl.getInstance();



    public static ItemService getInstance() {
        ItemService itemService = INSTANCE;
        if (itemService == null) {
            synchronized (ItemServiceImpl.class) {
                itemService = INSTANCE;
                if (itemService == null) {
                    INSTANCE = itemService = new ItemServiceImpl();
                }
            }
        }
        return itemService;
    }


    @Override
    public Item createItem(Item item, long customerId) { //сетаем айдишник клиента, которому пренадлежит этот узел
        item.setCustomerId(customerId);
        try{
            startTransaction();
            itemDAO.save(item);
            commit();
        } catch (SQLException e){
            rollback();
            throw new ServiceException("Ошибка создания заказчика " + item, e);
        }
        return item;
    }

    @Override
    public Item get(Serializable id) {
        return null;
    }

    @Override
    public void update(Item item) {

    }

    @Override
    public int delete(Serializable id) {
        return 0;
    }

    @Override
    public List<Item> getByCustomerId(long customerId) {
        try{

            return itemDAO.getByCustomerId(customerId);

        } catch(SQLException e){
            rollback();
            throw new ServiceException("Ошибка получения итемов по ид", e);
        }
    }

    @Override
    public List<Item> getAllItems() {
        try {
            startTransaction();
            List<Item> list = itemDAO.getAll();
            commit();
            return list;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting Products");
        }
    }
}
