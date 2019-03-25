package com.gavrilov.rest.providers;

import com.gavrilov.common.UserDetails;
import com.gavrilov.dao.UserDAO;
import com.gavrilov.model.User;
import org.glassfish.jersey.internal.util.Base64;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.util.*;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;
    @Inject
    private UserDAO userDAO;
    @Inject
    private UserDetails userDetails;

    private static final String ADMIN_LOGIN = "admin";
    private static final String USER_LOGIN = "user";
    private static final String PASSWORD_LOGIN = "123456";

    @Override
    public void filter(ContainerRequestContext requestContext) {
        Method resourceMethod = resourceInfo.getResourceMethod();
        if (!resourceMethod.isAnnotationPresent(PermitAll.class)) {
            if (resourceMethod.isAnnotationPresent(DenyAll.class)) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                        .entity("Доступ заблокирован для всех пользователей !!").build());
            } else if (resourceMethod.isAnnotationPresent(RolesAllowed.class)) {
                RolesAllowed rolesAnnotation = resourceMethod.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<>(Arrays.asList(rolesAnnotation.value()));
                Set<String> rolesSetTheUser = new HashSet<>();
                if (userDetails.userIsAuthorized()) {
                    rolesSetTheUser.addAll(userDetails.getRoleNames());
                } else {
                    User byLoginAndPassword = userDAO.findByLoginAndPassword(ADMIN_LOGIN, PASSWORD_LOGIN);
                    userDetails.setEnable(byLoginAndPassword.getEnable());
                    userDetails.setLogin(byLoginAndPassword.getLogin());
                    userDetails.setRoleNames(byLoginAndPassword.getRoleNames());
                    rolesSetTheUser.addAll(byLoginAndPassword.getRoleNames());
                }

                if (!isUserAllowed(rolesSet, rolesSetTheUser)) {
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                            .entity("Вы не можете получить доступ к этому ресурсу").build());
                }
            }
        }
    }

    private boolean isUserAllowed(final Set<String> rolesSetTheMethod, final Set<String> rolesSetTheUser) {
        boolean isAllowed = false;
        for (String roleName : rolesSetTheUser) {
            if (rolesSetTheMethod.contains(roleName)) {
                isAllowed = true;
                break;
            }
        }
        return isAllowed;
    }
}
