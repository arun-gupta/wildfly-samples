package org.samples.wildfly.jbossmodules.greet.simple;

import org.samples.wildfly.jbossmodules.greet.api.Greeter;

/**
 * @author Arun Gupta
 */
public class GreeterSimple implements Greeter {

    @Override
    public String greet(String name) {
        return "Hello5 " + name;
    }
    
}
