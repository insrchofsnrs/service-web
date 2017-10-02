package by.it.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * Created by Smile on 16.08.2017.
        */
@Data
@AllArgsConstructor
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
    public Customer(){}


}
