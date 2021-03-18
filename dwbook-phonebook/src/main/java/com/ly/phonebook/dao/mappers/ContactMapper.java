package com.ly.phonebook.dao.mappers;

import com.ly.representations.Contact;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class facilitate the mapping of a resultset database row to an object. It is the same mapping thing we use :( Not cool
 */
public class ContactMapper implements ResultSetMapper<Contact> {
    public Contact map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Contact(r.getInt("id"), r.getString("firstName"), r.getString("lastName"), r.getString("phone"));
    }
}
