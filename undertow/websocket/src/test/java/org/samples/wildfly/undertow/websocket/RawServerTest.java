package org.samples.wildfly.undertow.websocket;

import org.samples.wildfly.undertow.websocket.raw.UndertowWebSocketServer;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import javax.websocket.DeploymentException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Arun Gupta
 */
public class RawServerTest {

    private static final String BASE = "http://localhost:8080";

    @Test
    public void testEndpoint() throws IOException, ServletException, DeploymentException, InterruptedException {
        UndertowWebSocketServer server = new UndertowWebSocketServer();
        server.start();

        WebClient webClient = new WebClient();
        HtmlPage page = webClient.getPage(BASE + "/index.html");
        HtmlForm dataForm = page.getForms().get(0);
        HtmlButtonInput sendMessageButton = dataForm.getInputByName("button");
        sendMessageButton.click();

        System.out.println(page.getElementById("div").getTextContent());
        
        server.stop();
    }
}
