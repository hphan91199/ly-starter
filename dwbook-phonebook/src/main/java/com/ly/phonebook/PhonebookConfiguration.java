package com.ly.phonebook;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;

/**
 * This is where we map the json config for the whole application
 */
public class PhonebookConfiguration extends Configuration {

    @JsonProperty
    @NotEmpty
    private String message = "Default Message";

    @JsonProperty
    @Max(10)
    private int messageRepetitions;

    /**
     * DB Config here
     */
    @JsonProperty
    private DataSourceFactory database = new DataSourceFactory();

    public String getMessage() {
        return message;
    }

    public int getMessageRepetitions() {
        return messageRepetitions;
    }

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
}
