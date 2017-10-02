package com.systp.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Evgeni on 09.08.17.
 */
@Data
@AllArgsConstructor
public class Item {
    private long id;
    private long customerId;
    private double itemPrice;
    private String name;
    private String marking;
    private String label;
    private String status;
    public Item(){}
   // private java.sql.Date dataTime = new java.sql.Date(new Date().getTime());
}
