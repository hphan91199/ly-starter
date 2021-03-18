package com.ly.phonebook.resources;


import com.ly.representations.Contact;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This is where REST action defined. Where what does does defined
 *
 * <p>
 * RESTful Convention
 * <p>
 * POST = create
 * GET = get
 * PUT = update
 * DELETE = delete
 * <p>
 * HTTP Response Code
 * 201 Created (for POST)
 * 204 No Content (for DELETE and response sent to client does nto include an entity)
 * 200 OK (for GET)
 */

@Path("/contact")                       //URI Template (Uniform Resorce Identifier)
@Produces(MediaType.APPLICATION_JSON)   //Response Content Type
public class ContactResource3 {

    //GET
    @GET
    @Path("/{id}")
    public Response getContact(@PathParam("id") int id) {
        return Response.ok(new Contact(id, "FirstName", "LastName", "9123456")).build();
        //return Response.ok("{contact.id: " + id + ",name : \"Dummy Name\", phone: \"+0123456789\"}").build();
    }

    //SET]
    @POST
    public Response createContact(Contact contact) { //Now let it be a contact object
        return Response.created(null).build();
    }

    //DELETE
    @Path("/{id}")
    @DELETE
    public Response deleteContact(@PathParam("id") int id) {
        return Response.noContent().build();
    }
    //Reponse.noContent() return HTTP 204 No Content
    //Response.noContent() indidateces no content applicable to this request

    //UPDATE
    @Path("/{id}")
    @PUT
    public Response updateContact(@PathParam("id") int id, Contact contact) { // Let it be the contact object now
        return Response.status(200).entity(new Contact(id, contact.getFirstName(), contact.getLastName(), contact.getPhone())).build();
    }
    //Now I expect they pass in an object don't I? How? Of couse they will pass in a JSON string representing that object.
    //Who will translate for me? Jackson my dear. Jackson
    //How will my curl look? curl --header "Content-Type: application/json" -X PUT -d '{"firstName":"FOO", "lastName":"BAR", "phone":"91234567"}' http://localhost:8080/contact/123


}
