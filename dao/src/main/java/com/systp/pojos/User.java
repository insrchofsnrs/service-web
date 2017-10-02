package com.systp.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Evgeni on 09.08.17.
 */
@Data
@AllArgsConstructor
public class User {
    private long id;
    private String login;
    private String password;
    private String position;
   // private String status;
    private String firstName;
    private String secondName;
    private String role = "USER";
    public User(){}

}
