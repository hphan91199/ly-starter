package com.ly.phonebook.dao;

import com.ly.phonebook.dao.mappers.ContactMapper;
import com.ly.representations.Contact;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * This is DAO :) We know DAO
 */

public interface ContactDAO1 {

    @Mapper(ContactMapper.class)
    //Notice this Ly? It indicates ContactMapper to be used as mapper to translate from resultset to object (list)
    @SqlQuery("Select * from contact where id = :id") // this is the query executed when the method below called
    public Contact getContactById(@Bind("id") int id);


    @SqlUpdate("DELETE from contact where id = :id")
    void deleteContact(@Bind("id") int id);

    @SqlUpdate("Update contact set firstName = :firstName, lastName = :lastName, phone = :phone where id = :id")
    void updateContact(@Bind("id") int id, @Bind("firstName") String firstName, @Bind("lastName") String lastName, @Bind("phone") String phone);

    @GetGeneratedKeys
    @SqlUpdate("Insert Into contact(id, firstName, lastName, phone) values(NULL, :firstName, :lastName, :phone)")
    int createContact(@Bind("firstName") String firstName, @Bind("lastName") String lastName, @Bind("phone") String phone);
}
