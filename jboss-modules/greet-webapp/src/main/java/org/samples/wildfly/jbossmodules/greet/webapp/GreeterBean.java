package org.samples.wildfly.jbossmodules.greet.webapp;

import java.util.logging.Level;
import java.util.logging.Logger;
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
        Logger.getAnonymousLogger().log(Level.INFO, "GreeterBean.greet: " + name);
        return greeter.greet(name);
//        return "Hello " + name;
    }
}
