package com.xuben.nettyDemo.ioDemo;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class TimeServer {
    static final int PORT = Integer.parseInt(System.getProperty("port", "8081"));

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("TimeServer is started in port: " + PORT);

            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } finally {
            if (Objects.nonNull(serverSocket)) {
                System.out.println("the time server close");
                serverSocket.close();
                serverSocket = null;
            }
        }
    }
}
