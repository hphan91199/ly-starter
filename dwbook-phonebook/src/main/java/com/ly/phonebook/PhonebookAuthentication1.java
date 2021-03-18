package com.ly.phonebook;

import com.google.common.base.Optional;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;


public class PhonebookAuthentication1 implements Authenticator<BasicCredentials, Boolean> {


    public Optional<Boolean> authenticate(BasicCredentials c) {
        if (c.getUsername().equals("user") && c.getPassword().equals("secret"))
            return Optional.of(true);
        return Optional.absent();
    }
}
