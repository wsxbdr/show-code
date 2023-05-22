package java_io.bio_executor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer2 {

    static final int PORT = Integer.parseInt(System.getProperty("port", "8002"));

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("time server start!");
            Socket socket = null;
            TimeServerHandlerExecutePool executor = new TimeServerHandlerExecutePool(12, 100);
            while (true) {
                socket = serverSocket.accept();
                executor.execute(new TimeServerHandler2(socket));
            }

        } finally {

            if(serverSocket != null) {
                System.out.println("time server close!");
                serverSocket.close();
                serverSocket = null;
            }
        }

    }
}
