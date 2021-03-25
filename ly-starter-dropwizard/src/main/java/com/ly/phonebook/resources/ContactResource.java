package com.ly.phonebook.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.protobuf.InvalidProtocolBufferException;
import com.ly.protobuf.generated.DogProto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/contact")                       //URI Template (Uniform Resorce Identifier)
@Produces("application/json")             //Response Content Type
public class ContactResource {

    private static final Logger log = LoggerFactory.getLogger(ContactResource.class);

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

    //GET
    @GET
    @Path("/protolist")
    public Response getProtoList() throws JsonProcessingException, InvalidProtocolBufferException {
        List<DogProto.Dog> listDog = new ArrayList<>();
        List<String> jsonList = new ArrayList<>();
        DogProto.Dog d = DogProto.Dog.newBuilder().setAge(1).setName("Capi").build();
        var myReturn = com.google.protobuf.util.JsonFormat.printer().print(d);
        JsonObject object = new JsonParser().parse(myReturn).getAsJsonObject();
        return Response.ok(listDog).build();
    }

    //GET
    @GET
    @Path("/gson")
    public Response getGson() throws JsonProcessingException, InvalidProtocolBufferException {

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(com.google.protobuf.util.JsonFormat.printer().print(DogProto.Dog.newBuilder().setAge(1).setName("Prince").build()));
        sb.append(",");
        sb.append(com.google.protobuf.util.JsonFormat.printer().print(DogProto.Dog.newBuilder().setAge(1).setName("Pudding").build()));
        sb.append("]");
        return Response.ok(sb.toString()).build();
    }

    //GET
    @GET
    @Path("/googlejson")
    public Response getGoogleJson() throws JsonProcessingException, InvalidProtocolBufferException {
        List<DogProto.Dog> listDog = new ArrayList<>();
        listDog.add(DogProto.Dog.newBuilder().setAge(1).setName("Prince").build());
        listDog.add(DogProto.Dog.newBuilder().setAge(1).setName("Pudding ").build());

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(DogProto.Dog.class, new DogAdapter()).create();

        String gsonList = gson.toJson(listDog);
        System.out.println(gsonList);

        return Response.ok(gsonList).build();

    }

    @GET
    @Path("/integer/{id}")
    public Response TestInt(@PathParam("id") int id) {
        return Response.ok(id).build();
    }

    @GET
    @Path("/string/{id}")
    public Response testString(@PathParam("id") String id) {
        return Response.ok(id).build();
    }
}
