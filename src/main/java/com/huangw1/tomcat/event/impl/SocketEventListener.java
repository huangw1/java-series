package com.huangw1.tomcat.event.impl;

import com.huangw1.tomcat.event.AbstractEventListener;
import com.huangw1.tomcat.handler.EventHandler;
import java.net.Socket;

/**
 * Created by huangw1 on 2018/5/7.
 */
public class SocketEventListener extends AbstractEventListener<Socket> {

    private final EventHandler<Socket> eventHandler;

    public SocketEventListener(EventHandler<Socket> eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public EventHandler<Socket> getEventHandler(Socket socket) {
        return eventHandler;
    }
}
