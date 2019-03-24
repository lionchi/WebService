package com.gavrilov.rest.resources;

import com.gavrilov.dao.UrlDAO;
import com.gavrilov.model.Url;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("url")
public class UrlResource {

    @Inject
    private UrlDAO urlDAO;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response geUrlById(@PathParam("id") final Long id) {
        Url url = urlDAO.findById(id);
        return Response.ok(url).build();
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUrls() {
        List<Url> all = urlDAO.findAll();
        return Response.ok(all).build();
    }
}
