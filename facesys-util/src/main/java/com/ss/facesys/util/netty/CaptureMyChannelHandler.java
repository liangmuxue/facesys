package com.ss.facesys.util.netty;

import com.ss.facesys.util.em.Enums;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * WebSocket实现（Netty框架）
 *
 * @author FrancisYs
 * @date 2019/11/11
 * @email yaoshuai@ss-cas.com
 */
public class CaptureMyChannelHandler extends SimpleChannelInboundHandler<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaptureMyChannelHandler.class);

    private static WebSocketServerHandshaker handshaker;
    private static final String HOME_ADD = "/home";
    private static final int STATUS_CODE = 200;
    private static Map<String, List<ChannelHandlerContext>> CTX_MAP = new HashMap();

    /**
     * channel 助手类(拦截器)的添加
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        CaptureGlobalUserUtil.channels.add(ctx.channel());
    }

    /**
     * channel 助手类(拦截器)移除
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        CaptureGlobalUserUtil.channels.remove(ctx);
    }

    /**
     * 工程出现异常的时候调用
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        ctx.channel().close();
    }

    /**
     * 客户端与服务端创建连接的时候调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("【channelActive】=====>" + ctx.channel());
    }

    /**
     * 客户端与服务端断开连接的时候调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    }

    /**
     * 服务端接收客户端发送过来的数据结束之后调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * 服务端处理客户端WebSocket请求的核心方法（Netty5中channelRead0已被重命名为messageReceived）
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            doHandlerHttpRequest(ctx, (HttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {
            doHandlerWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    /**
     * 处理客户端与服务端之间的websocket业务
     * @param ctx
     * @param msg
     */
    private static void doHandlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame msg) {
        if (msg instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) msg);
            return;
        }
        if (msg instanceof PingWebSocketFrame) {
            PongWebSocketFrame pong = new PongWebSocketFrame(msg.content().retain());
            ctx.channel().writeAndFlush(pong);
            return;
        }
        if (msg instanceof PongWebSocketFrame) {
            PingWebSocketFrame ping = new PingWebSocketFrame(msg.content().retain());
            ctx.channel().writeAndFlush(ping);
            return;
        }
        if (!(msg instanceof TextWebSocketFrame)) {
            throw new UnsupportedOperationException("不支持二进制");
        }
        for (Channel channel : CaptureGlobalUserUtil.channels) {
            ctx.channel().writeAndFlush(new TextWebSocketFrame(((TextWebSocketFrame) msg).text()));
        }
    }

    /**
     * 处理客户端向服务端发起http握手请求的业务
     * @param ctx
     * @param msg
     */
    private void doHandlerHttpRequest(ChannelHandlerContext ctx, HttpRequest msg) {
        System.out.println("-------------------------msg.headers().get(\"Upgrade\"))--------------" + msg.headers().get("Validator"));
        if (!msg.getDecoderResult().isSuccess() || !"websocket".equalsIgnoreCase(msg.headers().get("Upgrade"))) {
            sendHttpResponse(ctx, (FullHttpRequest) msg, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
        }
        String uri = msg.getUri();
        if (!uri.substring(1).equals(Enums.NettyUri.URI_HOME.getCode()) && !uri.substring(1).equals(Enums.NettyUri.URI_OTHER.getCode())) {
            ctx.close();
        }
        ctx.attr(AttributeKey.valueOf("type")).set(uri);
        String flag = HOME_ADD.equals(uri) ? Enums.NettyUri.URI_HOME.getCode() : Enums.NettyUri.URI_OTHER.getCode();
        // 创建WebSocket握手工厂
        WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory("ws://" + msg.headers().get("Host") + "/" + flag + "", null, false);
        // 握手
        handshaker = factory.newHandshaker(msg);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
        }
        handshaker.handshake(ctx.channel(), (FullHttpRequest) msg);

        if (CTX_MAP.containsKey(flag)) {
            CTX_MAP.get(flag).add(ctx);
        } else {
            List<ChannelHandlerContext> ctxList = new ArrayList<>();
            ctxList.add(ctx);
            CTX_MAP.put(flag, ctxList);
        }
    }

    /**
     * 服务端主动向客户端发送消息
     * @param ctx
     * @param req
     * @param res
     */
    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        if (res.getStatus().code() != STATUS_CODE) {
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!HttpHeaders.isKeepAlive(req) || res.getStatus().code() != STATUS_CODE) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    /**
     * 消息推送
     * @param msg
     * @param upgrade
     */
    public static void send(String msg, String upgrade) {
        if (CTX_MAP.containsKey(upgrade)) {
            List<ChannelHandlerContext> ctxList = CTX_MAP.get(upgrade);
            TextWebSocketFrame tw = new TextWebSocketFrame(msg);
            for (ChannelHandlerContext ctx : ctxList) {
                ctx.channel().writeAndFlush(new TextWebSocketFrame(tw.text()));
            }
        } else {
            LOGGER.info("消息推送失败，未找到[" + upgrade + "]channel");
        }
    }

}
