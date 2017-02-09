package com.yx.wx.test.rpc.test;


import ch.qos.logback.core.net.SyslogOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class TcpClient {

    public void sentMsg(String msg) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("127.0.01", 9090));
            socket.setKeepAlive(true);
            OutputStream out = socket.getOutputStream();
//            ByteBuffer header = ByteBuffer.allocate(4);
//            header.putInt(msg.length);
//            out.write(header.array());
            for (int i=0;i<100;i++) {
                System.out.println(i);
                out.write((msg+i).getBytes());
                Thread.sleep(1);
            }
            out.write(("\n").getBytes());
            out.flush();
            InputStream in = socket.getInputStream();
            byte[] buff = new byte[1024];
            int readed = in.read(buff);
            if (readed > 0) {
                String str = new String(buff);
                System.out.println(str);
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TcpClient client = new TcpClient();
        client.sentMsg("hello:");
    }
}
