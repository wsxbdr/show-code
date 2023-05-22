package java_io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

    static final int PORT = Integer.parseInt(System.getProperty("port", "8001"));

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("the time server is start in port:" + PORT);
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } finally {

            if (serverSocket != null) {
                System.out.println("the time server close");
                serverSocket.close();
                serverSocket = null;
            }
        }
    }
}
