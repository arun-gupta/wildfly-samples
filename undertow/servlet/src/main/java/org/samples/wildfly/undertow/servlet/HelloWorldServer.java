package org.samples.wildfly.undertow.servlet;

import io.undertow.Undertow;
import io.undertow.servlet.Servlets;
import static io.undertow.servlet.Servlets.defaultContainer;
import static io.undertow.servlet.Servlets.deployment;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import javax.servlet.ServletException;

/**
 * @author Arun Gupta
 */
public class HelloWorldServer {

    Undertow server;
    
    DeploymentManager manager;

    public HelloWorldServer() {
        DeploymentInfo servletBuilder = deployment()
                .setClassLoader(HelloWorldServer.class.getClassLoader())
                .setContextPath("/helloworld")
                .setDeploymentName("helloworld.war")
                .addServlets(
                        Servlets.servlet("MyServlet", MyServlet.class)
                            .addInitParam("message", "Hello World")
                            .addMapping("/MyServlet"),
                        Servlets.servlet("MyAnotherServlet", MyAnotherServlet.class)
                            .addMapping("/MyAnotherServlet")
                );

        manager = defaultContainer().addDeployment(servletBuilder);
        manager.deploy ();
    }

    public void start() throws ServletException {
        server = Undertow.builder()
                .addListener(8080, "localhost")
                .setHandler(manager.start())
                .build();
        server.start();
    }

    public void stop() {
        server.stop();
    }
}
