package com.ly.phonebook.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/")
@Produces("application/json")             //Response Content Type
public class SnapshotResource {

  private static final Logger log = LoggerFactory.getLogger(SnapshotResource.class);

  //GET
  @GET
  @Path("{account_id}/{agent_id}/snapshot.json")
  public Response getSnapshot(@PathParam("account_id") int account,
      @PathParam("agent_id") int agent) {
    return Response.ok("{Account: " + account + ", agent: +" + agent + "}")
        .build();
  }

  //GET
  @GET
  @Path("/nothing")
  public Response getSnapshot2(@PathParam("account_id") int account,
      @PathParam("agent_id") int agent) {
    return Response.ok("{Account: " + account + ", agent: +" + agent + "}")
        .build();
  }
}
