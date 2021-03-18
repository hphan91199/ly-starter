package com.ly.phonebook.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

public interface UserDao {

    /**
     * The heck! Password is not encrypted, there is a call to DB all the time???
     */
    @SqlQuery("Select count(*) from users where username = :username and password = :password")
    int getUSer(@Bind("username") String username, @Bind("password") String password);
}
