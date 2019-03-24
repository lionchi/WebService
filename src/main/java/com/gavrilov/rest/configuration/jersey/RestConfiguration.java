package com.gavrilov.rest.configuration.jersey;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/service/")
public class RestConfiguration extends ResourceConfig {
    public RestConfiguration() {
        packages(false, "com.gavrilov.rest.resources");
        register(MultiPartFeature.class);
    }
}
