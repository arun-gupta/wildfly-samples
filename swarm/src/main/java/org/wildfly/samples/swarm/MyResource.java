package org.wildfly.samples.swarm;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author arungupta
 */
@Path("/resource")
public class MyResource {
    @GET
    public String get() {
        return "hello swarm!";
    }
}
