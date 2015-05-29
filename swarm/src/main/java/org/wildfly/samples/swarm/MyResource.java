package org.wildfly.samples.swarm;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author arungupta
 */
@Path("/resource")
public class MyResource {
    
    @PersistenceContext
    EntityManager em;
    
    public String get() {
        return "hello swarm!";
    }
    
    @GET
    public List<Employee> getAll() {
        return em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }
}
