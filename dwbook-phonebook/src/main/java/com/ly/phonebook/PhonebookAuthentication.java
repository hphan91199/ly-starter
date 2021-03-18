package com.ly.phonebook;

import com.google.common.base.Optional;
import com.ly.phonebook.dao.UserDao;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import org.skife.jdbi.v2.DBI;


public class PhonebookAuthentication implements Authenticator<BasicCredentials, Boolean> {

    private final UserDao userDao;

    public PhonebookAuthentication(DBI jdbi) {
        userDao = jdbi.onDemand((UserDao.class));

    }

    public Optional<Boolean> authenticate(BasicCredentials c) {
//        if (c.getUsername().equals("user") && c.getPassword().equals("secret"))
//            return Optional.of(true);

        boolean validUser = (userDao.getUSer(c.getUsername(), c.getPassword())) == 1;
        if (validUser)
            return Optional.of(true);

        return Optional.absent();
    }
}
