package org.samples.wildfly.undertow.websocket.raw;

import io.undertow.Undertow;
import javax.servlet.ServletException;
import static io.undertow.Handlers.*;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.websockets.core.AbstractReceiveListener;
import io.undertow.websockets.core.BufferedTextMessage;
import io.undertow.websockets.core.WebSocketChannel;
import io.undertow.websockets.core.WebSockets;
import io.undertow.websockets.core.handler.WebSocketConnectionCallback;
import io.undertow.websockets.spi.WebSocketHttpExchange;

/**
 * @author Arun Gupta
 */
public class UndertowWebSocketServer {

    Undertow server;

    public UndertowWebSocketServer() {
        server = Undertow.builder()
                .addListener(8080, "localhost")
                .setHandler(path()
                        .addPath("/myapp", websocket(new WebSocketConnectionCallback() {

                            @Override
                            public void onConnect(WebSocketHttpExchange exchange, WebSocketChannel channel) {
                                channel.getReceiveSetter().set(new AbstractReceiveListener() {

                                    @Override
                                    protected void onFullTextMessage(WebSocketChannel channel, BufferedTextMessage message) {
                                        WebSockets.sendText(message.getData(), channel, null);
                                    }
                                });
                                channel.resumeReceives();
                            }
                        }))
                        .addPath("/", 
                                resource(
                                        new ClassPathResourceManager(
                                                UndertowWebSocketServer.class.getClassLoader(), 
                                                UndertowWebSocketServer.class.getPackage()))
                                        .addWelcomeFiles("index.html")))
                .build();
    }

    public void start() throws ServletException {
        server.start();
    }

    public void stop() {
        server.stop();
    }
}
