package com.systp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by Smile on 16.08.2017.
        */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    private long id;
    private String organization;
    private String firstName;
    private String secondName;
    private String phoneNumberA;
    private String phoneNumberB;
    private String phoneNumberFax;
    private String email;
    private String position;
   // private Date date;



}
