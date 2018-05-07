package com.huangw1.tomcat.impl;

import com.huangw1.tomcat.Server;
import com.huangw1.tomcat.ServerStatus;
import com.huangw1.tomcat.config.ServerConfig;
import com.huangw1.tomcat.io.IoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by huangw1 on 2018/5/7.
 */
public class SimpleServer implements Server {

    private static Logger logger = LoggerFactory.getLogger(SimpleServer.class);

    private volatile ServerStatus serverStatus = ServerStatus.STOP;

    private ServerSocket serverSocket;

    private int port;

    public SimpleServer(ServerConfig config) {
        this.port = config.getPort();
    }

    @Override
    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
        serverStatus = ServerStatus.START;

        while(true) {
            Socket socket = serverSocket.accept();
            logger.info("新增连接：{}:{}", socket.getInetAddress(), port);
            IoUtil.closeQuietly(socket);
        }
    }

    @Override
    public void stop() {
        IoUtil.closeQuietly(serverSocket);
        serverStatus = ServerStatus.STOP;
    }

    @Override
    public ServerStatus getStatus() {
        return serverStatus;
    }

    @Override
    public int getPort() {
        return port;
    }
}
