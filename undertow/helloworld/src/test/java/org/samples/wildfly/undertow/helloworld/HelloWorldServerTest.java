package org.samples.wildfly.undertow.helloworld;

import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Arun Gupta
 */
public class HelloWorldServerTest {

    @Test
    public void testApp() throws IOException {
        HelloWorldServer server = new HelloWorldServer();
        server.start();
        WebClient webClient = new WebClient();
        TextPage page = webClient.getPage("http://localhost:8080");
        assertEquals("Hello World", page.getContent());
        server.stop();
    }
}
