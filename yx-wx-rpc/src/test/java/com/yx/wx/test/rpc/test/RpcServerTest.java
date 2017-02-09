package com.yx.wx.test.rpc.test;


import com.yx.wx.rpc.server.RpcServer;
import com.yx.wx.rpc.server.TcpServer;

public class RpcServerTest {

    public static void main(String[] args) {
        RpcServer server = new TcpServer();
        server.setPort(9090);
        server.run();
    }
}
