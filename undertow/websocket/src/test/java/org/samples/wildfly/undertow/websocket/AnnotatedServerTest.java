package org.samples.wildfly.undertow.websocket;

import org.samples.wildfly.undertow.websocket.annotated.AnnotatedWebSocketServer;
import org.samples.wildfly.undertow.websocket.annotated.MyClient;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Arun Gupta
 */
public class AnnotatedServerTest {

    @Test
    public void testEndpoint() throws IOException, ServletException, DeploymentException, InterruptedException {
        AnnotatedWebSocketServer server = new AnnotatedWebSocketServer();
        server.start();

        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        String uri = "ws://localhost:8080/server";
        System.out.println("Connecting to " + uri);
        container.connectToServer(MyClient.class, URI.create(uri));

//        WebClient webClient = new WebClient();
//        TextPage page = webClient.getPage("http://localhost:8080/myapp/server");
//        assertEquals("Hello World", page.getContent());
        assertTrue(AnnotatedWebSocketServer.LATCH.await(3, TimeUnit.SECONDS));
        server.stop();
    }
}
