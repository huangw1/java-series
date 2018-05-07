package com.huangw1.tomcat.io.impl.socket;

import com.huangw1.tomcat.event.EventListener;
import com.huangw1.tomcat.io.AbstractConnector;
import com.huangw1.tomcat.io.ConnectorException;
import com.huangw1.tomcat.io.IoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by huangw1 on 2018/5/7.
 */

/**
 * 责任分离
 * SocketConnector 负责管理 connect
 */
public class SocketConnector extends AbstractConnector<Socket> {

    private static final Logger logger = LoggerFactory.getLogger(SocketConnector.class);

    private static final String LOCALHOST = "localhost";

    private static final int DEFAULT_BACKLOG = 50;

    private ServerSocket serverSocket;

    private final EventListener<Socket> eventListener;

    private final int port;

    private final String host;

    private final int backLog;

    private volatile boolean started = false;

    public SocketConnector(int port, EventListener<Socket> eventListener) {
        this(port, LOCALHOST, DEFAULT_BACKLOG, eventListener);
    }

    public SocketConnector(int port, String host, int backLog, EventListener<Socket> eventListener) {
        this.port = port;
        this.host = host;
        this.backLog = backLog;
        this.eventListener = eventListener;
    }

    @Override
    public void init() throws ConnectorException {
        try {
            InetAddress inetAddress = InetAddress.getByName(host);
            serverSocket = new ServerSocket(port, backLog, inetAddress);
            started = true;
        } catch (IOException e) {
            throw new ConnectorException(e);
        }
    }

    @Override
    public void acceptConnect() throws ConnectorException {
        new Thread(() -> {
            while(true && started) {
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                    communicate(socket);
                } catch (IOException e) {
                    // 单个异常不影响全局
                    logger.error(e.getMessage(), e);
                } finally {
                    IoUtil.closeQuietly(socket);
                }
            }
        }).start();
    }

    @Override
    public void communicate(Socket socket) {
        eventListener.onEvent(socket);
    }

    @Override
    public void stop() {
        IoUtil.closeQuietly(serverSocket);
        started = false;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public String getHost() {
        return host;
    }
}
