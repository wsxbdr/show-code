package com.xuben.nettyDemo.ioDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TimeClient {

    static final String cmd = "query time";
    static final int PORT = Integer.parseInt(System.getProperty("port", "8081"));

    public static void main(String[] args) {

        BufferedReader in = null;
        PrintWriter out = null;
        Socket socket = null;

        try {
            socket = new Socket("127.0.0.1", PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(cmd);
            System.out.println("send success!");
            String resp = in.readLine();
            System.out.println("now is: " + resp);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (out != null) {
                out.close();
                out = null;
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
