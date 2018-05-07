package com.huangw1.tomcat;

import com.huangw1.tomcat.config.ServerConfig;
import com.huangw1.tomcat.io.IoUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import static org.junit.Assert.*;

/**
 * Created by huangw1 on 2018/5/7.
 */
public class TestServerAcceptRequest extends TestServerBase {

    private static Logger logger = LoggerFactory.getLogger(TestServerAcceptRequest.class);

    private static Server server;

    private static final int TIMEOUT = 1000;

    @BeforeClass
    public static void init() {
        ServerConfig serverConfig = new ServerConfig();
        server = ServerFactory.getServer(serverConfig);
    }

    @Test
    public void testServerAcceptRequest() {
        if(server.getStatus().equals(ServerStatus.STOP)) {
            startServer(server);
            waitServerStart(server);

            Socket socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress("localhost", ServerConfig.DEFAULT_PORT);
            try {
                socket.connect(socketAddress, TIMEOUT);
                assertTrue("连接正常，接受请求", socket.isConnected());
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            } finally {
                IoUtil.closeQuietly(socket);
            }
        }
    }

    @AfterClass
    public static void destroy() {
        server.stop();
    }
}
