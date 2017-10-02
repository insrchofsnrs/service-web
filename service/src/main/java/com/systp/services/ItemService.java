package com.systp.services;

import by.it.entities.Item;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Evgeni on 31.08.17.
 */
public interface ItemService {
    Item createItem(Item item, long customerId);
    Item get(Serializable id);
    void update(Item item);
    int delete(Serializable id);
    List<Item> getByCustomerId(long customerId);
    List<Item> getAllItems();


}
