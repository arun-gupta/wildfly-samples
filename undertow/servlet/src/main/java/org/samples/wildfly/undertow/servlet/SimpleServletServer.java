package org.samples.wildfly.undertow.servlet;

import io.undertow.Undertow;
import io.undertow.servlet.Servlets;
import static io.undertow.servlet.Servlets.defaultContainer;
import static io.undertow.servlet.Servlets.deployment;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;

/**
 * @author Arun Gupta
 */
public class SimpleServletServer {

    Undertow server;
    
    public SimpleServletServer() {
        DeploymentInfo deploymentInfo = deployment()
                .setClassLoader(SimpleServletServer.class.getClassLoader())
                .setContextPath("/helloworld")
                .setDeploymentName("helloworld.war")
                .addServlets(
                        Servlets.servlet("MyServlet", MyServlet.class)
                            .addInitParam("message", "Hello World")
                            .addMapping("/MyServlet"),
                        Servlets.servlet("MyAnotherServlet", MyAnotherServlet.class)
                            .addMapping("/MyAnotherServlet")
                );

        DeploymentManager manager = defaultContainer().addDeployment(deploymentInfo);
        manager.deploy ();
        try {
            server = Undertow.builder()
                    .addListener(8080, "localhost")
                    .setHandler(manager.start())
                    .build();
        } catch (ServletException ex) {
            Logger.getLogger(SimpleServletServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start() {
        server.start();
    }

    public void stop() {
        server.stop();
    }
}
