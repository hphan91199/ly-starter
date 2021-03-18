package com.ly.phonebook.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.ly.generated.DogProto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
public class ContactResource {

    //GET
    @GET
    @Path("/{id}")
    public Response getContact(@PathParam("id") int id) {
        return Response.ok("{contact.id: " + id + ",name : \"Dummy Name\", phone: \"+0123456789\"}").build();
    }


    //GET
    @GET
    @Path("/noconversion")
    public Response getProto3() throws JsonProcessingException, InvalidProtocolBufferException {
        DogProto.Dog d = DogProto.Dog.newBuilder().setAge(1).setName("Capi").build();
        return Response.ok(d).build();
    }

    //GET
    @GET
    @Path("/proto")
    public Response getProto() throws JsonProcessingException, InvalidProtocolBufferException {
        DogProto.Dog d = DogProto.Dog.newBuilder().setAge(1).setName("Capi").build();
        return Response.ok(com.google.protobuf.util.JsonFormat.printer().print(d)).build();
    }
}
