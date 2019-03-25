package com.gavrilov.rest.resources;

import com.gavrilov.common.UserDetails;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashSet;

@Path("user")
public class UserResource {

    @Inject
    private UserDetails userDetails;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/auth")
    public Response authUser() {
        userDetails.setLogin("admin");
        userDetails.setEnable(1);
        userDetails.setRoleNames(new HashSet<>(Arrays.asList("ADMIN", "USER")));
        return Response.ok("Вы успешно авторизовались").build();
    }
}
