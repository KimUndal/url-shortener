package com.undal;

import com.undal.resource.UrlShortenerDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface UrlShortenerApi {

    @GET
    @Path("{alias}")
    Response getUrlShortener(@PathParam("alias") String alias);

    @POST
    Response createUrlShortener(UrlShortenerDTO urlShortener);
}
