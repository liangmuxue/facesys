package com.ss.facesys.util.netty;

import com.ss.facesys.util.thread.SysThreadPool;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * CaptureNettyServer
 *
 * @author FrancisYs
 * @date 2019/11/11
 * @email yaoshuai@ss-cas.com
 */
@Component
public class CaptureNettyServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaptureNettyServer.class);

    @Value("${netty.server.port}")
    public Integer port;

    @Value("${system.pram.threadPoolSize}")
    private int threadPoolSize;
    @Value("${system.pram.threadMaxPoolSize}")
    private int threadMaxPoolSize;
    @Value("${system.pram.threadAliveTime}")
    private int threadAliveTime;

    public Integer getPort() {
        return this.port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public int getThreadPoolSize() {
        return this.threadPoolSize;
    }

    public void setThreadPoolSize(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    public int getThreadMaxPoolSize() {
        return this.threadMaxPoolSize;
    }

    public void setThreadMaxPoolSize(int threadMaxPoolSize) {
        this.threadMaxPoolSize = threadMaxPoolSize;
    }

    public int getThreadAliveTime() {
        return this.threadAliveTime;
    }

    public void setThreadAliveTime(int threadAliveTime) {
        this.threadAliveTime = threadAliveTime;
    }


    @PostConstruct
    public void init() {
        StartTask task = new StartTask(this.port);
        SysThreadPool.init(this.threadPoolSize, this.threadMaxPoolSize, this.threadAliveTime);
        SysThreadPool.getThread().execute(task);
    }

    static class StartTask implements Runnable {

        private int taskPport;

        public StartTask(int taskPport) {
            this.taskPport = taskPport;
        }

        public int getTaskPport() {
            return this.taskPport;
        }

        public void setTaskPport(int taskPport) {
            this.taskPport = taskPport;
        }

        @Override
        public void run() {
            NioEventLoopGroup nioEventLoopGroup1 = new NioEventLoopGroup();
            NioEventLoopGroup nioEventLoopGroup2 = new NioEventLoopGroup();
            try {
                ServerBootstrap bootstrap = new ServerBootstrap();
                bootstrap.group(nioEventLoopGroup1, nioEventLoopGroup2).channel(io.netty.channel.socket.nio.NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast("http-codec", new HttpServerCodec());
                        socketChannel.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
                        socketChannel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                        socketChannel.pipeline().addLast(new IdleStateHandler(60L, 30L, 1800L, TimeUnit.SECONDS));
                        socketChannel.pipeline().addLast(new CaptureMyChannelHandler());
                    }
                }).option(ChannelOption.SO_BACKLOG, 1024).childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);
                LOGGER.info("【服务器启动成功========端口：" + this.taskPport + "】");
                Channel channel = bootstrap.bind(this.taskPport).sync().channel();
                channel.closeFuture().sync();
            } catch (Exception e) {
                LOGGER.error(e.toString(), e);
            } finally {
                nioEventLoopGroup1.shutdownGracefully();
                nioEventLoopGroup2.shutdownGracefully();
            }
        }

    }

}
