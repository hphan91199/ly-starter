package com.ly.representations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dropwizard.validation.ValidationMethod;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * This is the model of it all
 * We want it to have same properties as the JSON object we wish to generate and return to client
 * Any POJO can be used as a representation.
 * Jackson will handle the transformation from POJO to JSON transparently
 */
public class Contact {


    private int id;

    @NotBlank //Hybernate Validator constraints
    @Length(min = 2, max = 255)
    private String firstName;

    @NotBlank //Hybernate Validator constraints
    @Length(min = 2, max = 255)
    private String lastName;

    @NotBlank //Hybernate Validator constraints
    @Length(min = 2, max = 30)
    private String phone;

    @JsonIgnore
    private String innerVariableNotExpose;

    public Contact() {

    }

    public Contact(int id, String firstName, String lastName, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    /**
     * A validation method to work must return boolean and start with is. Remember to put JsonIgnore
     */
    @JsonIgnore
    @ValidationMethod(message = "Join Doe is not a valid name")
    public boolean isValidName() {
        if ("John".equals(firstName) && "Doe".equals((lastName)))
            return false;
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonIgnore
    public String getInnerVariableNotExpose() {
        return innerVariableNotExpose;
    }

    public void setInnerVariableNotExpose(String innerVariableNotExpose) {
        this.innerVariableNotExpose = innerVariableNotExpose;
    }
}
