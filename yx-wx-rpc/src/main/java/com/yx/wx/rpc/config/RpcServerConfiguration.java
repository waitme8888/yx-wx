package com.yx.wx.rpc.config;

import com.yx.wx.rpc.server.RpcServer;
import com.yx.wx.rpc.server.TcpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RpcServerProperties.class)
public class RpcServerConfiguration {

    @Autowired
    private RpcServerProperties properties;

    @Bean("TcpServer")
    @ConditionalOnProperty(prefix = "rpc.server", value = "port")
    public RpcServer tcpServer() {
        RpcServer server = new TcpServer();
        server.setPort(properties.getPort());
        server.run();
        return server;
    }
}
