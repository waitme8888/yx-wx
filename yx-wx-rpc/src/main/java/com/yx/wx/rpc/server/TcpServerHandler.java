package com.yx.wx.rpc.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TcpServerHandler extends SimpleChannelInboundHandler<Object> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public TcpServerHandler() {

    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        logger.info("channelRegistered:{}",ctx.channel().toString());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        logger.info("channelUnregistered:{}",ctx.channel().toString());
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) {
        logger.info(msg.toString());
        ctx.write(msg);
        ctx.flush();
    }

}
