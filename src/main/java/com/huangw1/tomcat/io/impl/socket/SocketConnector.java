package com.huangw1.tomcat.io.impl.socket;

import com.huangw1.tomcat.io.Connector;
import com.huangw1.tomcat.io.ConnectorException;
import com.huangw1.tomcat.io.IoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by huangw1 on 2018/5/7.
 */
public class SocketConnector extends Connector {

    private static Logger logger = LoggerFactory.getLogger(SocketConnector.class);

    private ServerSocket serverSocket;

    private int port;

    private boolean started = false;

    public SocketConnector(int port) {
        this.port = port;
    }

    @Override
    public void init() throws ConnectorException {
        try {
            serverSocket = new ServerSocket(port);
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
                    logger.info("新增连接：{}:{}", socket.getInetAddress(), port);
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
    public void stop() {
        IoUtil.closeQuietly(serverSocket);
        started = false;
    }
}
