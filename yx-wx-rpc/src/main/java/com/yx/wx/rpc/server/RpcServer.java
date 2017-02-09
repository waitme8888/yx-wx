package com.yx.wx.rpc.server;


public interface RpcServer {

    void run();

    void shutdown();

    String getHost();

    void setHost(String host);

    int getPort();

    void setPort(int port);

}
