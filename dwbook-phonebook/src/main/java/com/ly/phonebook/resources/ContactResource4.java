package com.ly.phonebook.resources;


import com.ly.phonebook.dao.ContactDAO;
import com.ly.representations.Contact;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/contact")                       //URI Template (Uniform Resorce Identifier)
@Produces(MediaType.APPLICATION_JSON)   //Response Content Type
public class ContactResource4 {

    private final ContactDAO contactDao;

    public ContactResource4(DBI jdbi) {
        contactDao = jdbi.onDemand(ContactDAO.class);
    }

    @GET
    @Path("/{id}")
    public Response getContact(@PathParam("id") int id) {
        Contact contact = contactDao.getContactById(id);
        return Response.ok(contact).build();
    }

    @POST
    public Response createContact(Contact contact) throws URISyntaxException {
        int newContactId = contactDao.createContact(contact.getFirstName(), contact.getLastName(), contact.getPhone());
        return Response.created(new URI(String.valueOf(newContactId))).build();
    }

    @Path("/{id}")
    @DELETE
    public Response deleteContact(@PathParam("id") int id) {
        contactDao.deleteContact(id);
        return Response.noContent().build();
    }


    @Path("/{id}")
    @PUT
    public Response updateContact(@PathParam("id") int id, Contact contact) {
        contactDao.updateContact(contact.getId(), contact.getFirstName(), contact.getLastName(), contact.getPhone());
        return Response.status(200).entity(new Contact(id, contact.getFirstName(), contact.getLastName(), contact.getPhone())).build();
    }


}
