package com.huangw1.tomcat.handler.impl;

import com.huangw1.tomcat.handler.AbstractEventHandler;
import com.huangw1.tomcat.handler.HandlerException;
import com.huangw1.tomcat.io.IoUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by huangw1 on 2018/5/7.
 */

public class EchoHandler extends AbstractEventHandler<Socket> {

    @Override
    protected void doHandler(Socket socket) {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            printStream.append("Server connected.");
            printStream.flush();
        } catch (IOException e) {
            throw new HandlerException(e);
        } finally {
            IoUtil.closeQuietly(inputStream);
            IoUtil.closeQuietly(outputStream);
        }
    }
}
