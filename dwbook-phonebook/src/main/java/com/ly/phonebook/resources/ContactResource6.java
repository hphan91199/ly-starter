package com.ly.phonebook.resources;


import com.ly.phonebook.dao.ContactDAO;
import com.ly.representations.Contact;
import io.dropwizard.auth.Auth;
import org.skife.jdbi.v2.DBI;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Now with validator
 */

@Path("/contact")                       //URI Template (Uniform Resorce Identifier)
@Produces(MediaType.APPLICATION_JSON)   //Response Content Type
public class ContactResource6 {

    private final ContactDAO contactDao;
    private final Validator validator;

    public ContactResource6(DBI jdbi, Validator validator) {
        contactDao = jdbi.onDemand(ContactDAO.class);
        this.validator = validator;
    }

    @GET
    @Path("/{id}")
    public Response getContact(@PathParam("id") int id, @Auth(required = false) Boolean isAuthenticated) { //See how I say it must be authenticated? it could be optional too
        Contact contact = contactDao.getContactById(id);
        return Response.ok(contact).build();
    }

    @POST
    public Response createContact(Contact contact, @Auth Boolean isAuthenticated) throws URISyntaxException {
        Set<ConstraintViolation<Contact>> violations = validator.validate(contact); //Validation here dear!
        if (violations.size() > 0) {
            List<String> validationMessage = new ArrayList<String>();
            for (ConstraintViolation<Contact> violation : violations) {
                validationMessage.add(violation.getPropertyPath().toString() + ":" + violation.getMessage());
            }
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(validationMessage).build();
        } else {
            int newContactId = contactDao.createContact(contact.getFirstName(), contact.getLastName(), contact.getPhone());
            return Response.created(new URI(String.valueOf(newContactId))).build();
        }
    }

    @Path("/{id}")
    @DELETE
    public Response deleteContact(@PathParam("id") int id, @Auth Boolean isAuthenticated) {
        contactDao.deleteContact(id);
        return Response.noContent().build();
    }


    @Path("/{id}")
    @PUT
    public Response updateContact(@PathParam("id") int id, Contact contact, @Auth Boolean isAuthenticated) {
        contactDao.updateContact(contact.getId(), contact.getFirstName(), contact.getLastName(), contact.getPhone());
        return Response.status(200).entity(new Contact(id, contact.getFirstName(), contact.getLastName(), contact.getPhone())).build();
    }


}
