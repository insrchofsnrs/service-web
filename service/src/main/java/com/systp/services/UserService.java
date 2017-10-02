package com.systp.services;

import by.it.entities.User;

/**
 * Created by Smile on 27.08.2017.
 */
public interface UserService {
    User getByLogin(String login);
}
