package org.samples.wildfly.jbossmodules.greet.webapp;

import javax.inject.Inject;
import javax.inject.Named;
import org.samples.wildfly.jbossmodules.greet.api.Greeter;

/**
 * @author Arun Gupta
 */
@Named
public class GreeterBean {
    @Inject Greeter greeter;
    
    public String greet(String name) {
        return greeter.greet(name);
    }
}
