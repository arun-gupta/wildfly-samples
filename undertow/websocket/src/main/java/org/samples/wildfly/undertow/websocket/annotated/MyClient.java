package org.samples.wildfly.undertow.websocket.annotated;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 * @author Arun Gupta
 */
@ClientEndpoint
public class MyClient {
    
    @OnOpen
    public void onOpen(Session session) {
//        HelloWorldServer.LATCH.countDown();
        System.out.println("Connected to endpoint: " + session.getBasicRemote());
        try {
            String name = "Sheldon";
            System.out.println("Sending message to endpoint: " + name);
            session.getBasicRemote().sendText(name);
        } catch (IOException ex) {
            Logger.getLogger(MyClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
