package org.samples.wildfly.undertow.servlet;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import java.io.IOException;
import java.net.URL;
import javax.servlet.ServletException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Arun Gupta
 */
public class SimpleServletServerTest {
    
    private static final String BASE = "http://localhost:8080";

    @Test
    public void testMyServlet() throws IOException, ServletException {
        SimpleServletServer server = new SimpleServletServer();
        server.start();
        WebClient webClient = new WebClient();
        TextPage page = webClient.getPage(BASE + "/MyServlet");
        assertEquals("Hello World from GET", page.getContent());
        
        WebRequest request = new WebRequest(new URL(BASE + "/MyServlet"), HttpMethod.POST);
        page = webClient.getPage(request);
        assertEquals("Hello World from POST", page.getContent());
        server.stop();
    }


    @Test
    public void testMyAnotherServlet() throws IOException, ServletException {
        SimpleServletServer server = new SimpleServletServer();
        server.start();
        WebClient webClient = new WebClient();
        TextPage page = webClient.getPage(BASE + "/MyAnotherServlet");
        assertEquals("Howdy World from GET", page.getContent());
        
        WebRequest request = new WebRequest(new URL(BASE + "/MyAnotherServlet"), HttpMethod.POST);
        page = webClient.getPage(request);
        assertEquals("Howdy World from POST", page.getContent());
        server.stop();
    }
}
