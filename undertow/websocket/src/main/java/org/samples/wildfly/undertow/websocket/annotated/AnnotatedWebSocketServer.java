package org.samples.wildfly.undertow.websocket.annotated;

import io.undertow.Undertow;
import static io.undertow.servlet.Servlets.defaultContainer;
import static io.undertow.servlet.Servlets.deployment;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import javax.servlet.ServletException;
import org.xnio.OptionMap;
import org.xnio.Xnio;
import org.xnio.XnioWorker;

/**
 * @author Arun Gupta
 */
public class AnnotatedWebSocketServer {

    Undertow server;
    DeploymentManager manager;
    public static final CountDownLatch LATCH = new CountDownLatch(3);

    public AnnotatedWebSocketServer() throws IOException {
        final Xnio xnio = Xnio.getInstance("nio", Undertow.class.getClassLoader());
        final XnioWorker xnioWorker = xnio.createWorker(OptionMap.builder().getMap());

        WebSocketDeploymentInfo websocket = new WebSocketDeploymentInfo()
                .addEndpoint(MyAnnotatedEndpoint.class)
                .setWorker(xnioWorker);

        DeploymentInfo deploymentInfo = deployment()
                .setClassLoader(MyAnnotatedEndpoint.class.getClassLoader())
                .setContextPath("/myapp")
                .setDeploymentName("websockets")
                .addServletContextAttribute(WebSocketDeploymentInfo.ATTRIBUTE_NAME, websocket);

        manager = defaultContainer().addDeployment(deploymentInfo);
        manager.deploy();
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
