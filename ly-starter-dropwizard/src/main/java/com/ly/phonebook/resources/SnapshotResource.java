package com.ly.phonebook.resources;

import java.util.List;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/snapshot")
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
  @Path("/test")
  public Response getSnapshot2(@DefaultValue("-1") @QueryParam("account_id") int account,
      @DefaultValue("-1") @QueryParam("agent_id") long agent) {
    if (account == -1 || agent == -1)
      return Response.status(Status.BAD_REQUEST).entity("Invalid parameters").build();
    return Response.ok("{Account: " + account + ", agent: +" + agent + "}")
        .build();
  }

    @GET
    @Path("/query")
    public Response getUsers(
        @QueryParam("from") int from,
        @QueryParam("to") int to,
        @QueryParam("orderBy") List<String> orderBy) {

      return Response
          .status(200)
          .entity("getUsers is called, from : " + from + ", to : " + to
              + ", orderBy" + orderBy.toString()).build();

    }
}
