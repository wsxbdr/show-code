package com.xuben.nettyDemo.ioDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Objects;

public class TimeServerHandler implements Runnable {

    private Socket socket;
    static final String cmd = "query time";

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String currentTime = null;
            String body = null;
            while (true) {
                if ((body = in.readLine()) != null) {
                    System.out.println("the time server receive order:" + body);
                    currentTime = cmd.equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                    out.println(currentTime);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
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
            if (this.socket != null) {
                try {
                    this.socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                this.socket = null;
            }
            e.printStackTrace();
        }
    }
}
