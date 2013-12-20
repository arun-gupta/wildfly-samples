package org.samples.wildfly.undertow.websocket.annotated;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

/**
 * @author Arun Gupta
 */
@ServerEndpoint("/server")
public class MyAnnotatedEndpoint {
    @OnOpen
    public void onOpen() {
        AnnotatedWebSocketServer.LATCH.countDown();
        System.out.println("onOpen");
    }

    @OnClose
    public void onClose() {
      System.out.println("onClose");
    }

    @OnMessage
    public void onMessage(String message) {
        AnnotatedWebSocketServer.LATCH.countDown();
        System.out.println("onMessage: " + message);
    }
}
