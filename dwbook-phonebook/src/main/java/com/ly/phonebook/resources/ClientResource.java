package com.ly.phonebook.resources;


import com.ly.phonebook.views.ContactView;
import com.ly.representations.Contact;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This class will create human friendly return for HTTP
 */
@Path("/client/")
@Produces(MediaType.TEXT_HTML)
public class ClientResource {
    private Client client;

    public ClientResource(Client client) {
        this.client = client;
    }

    @GET
    @Path("showContact")
    public ContactView showContact(@QueryParam("id") int id) {
        WebResource contactResource = client.resource("http://localhost:8080/contact/" + id);
        Contact c = contactResource.get(Contact.class);
        //String output = String.format("ID: %s \nFirst Name: %s\nLast Name: %s\nPhone:%s\nPhone:%s", id, c.getFirstName(), c.getLastName(), c.getPhone());
        return new ContactView(contactResource.get(Contact.class));
    }

    @GET
    @Path("newContact")
    public Response newContact(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName, @QueryParam("phone") String phone) {
        WebResource contactResource = client.resource("http://localhost:8080/contact");
        ClientResponse response = contactResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, new Contact(0, firstName, lastName, phone));
        if (response.getStatus() == 201) {
            return Response.status(302).entity("The contact was created successfully! The new contact can be found at " + response.getHeaders().getFirst("Location")).build();
        } else {
            return Response.status(442).entity(response.getEntity(String.class)).build();
        }
    }
}
