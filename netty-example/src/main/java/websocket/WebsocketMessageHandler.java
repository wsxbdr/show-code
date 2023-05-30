package websocket;

import com.google.gson.Gson;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler.HandshakeComplete;
import io.netty.util.AttributeKey;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xuben
 * @description TODO
 * @date 2023/5/25 14:10
 */
@Slf4j
public class WebsocketMessageHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

  private static final AttributeKey<String> TOKEN = AttributeKey.valueOf("token");
  private static final AttributeKey<String> AREA_ID = AttributeKey.valueOf("areaId");

  private static final Map<String, Channel> map = new ConcurrentHashMap<>();

  private static final Gson GSON = new Gson();

  @Override
  protected void channelRead0(ChannelHandlerContext ctx,
      WebSocketFrame frame) throws Exception {
    if (frame instanceof PingWebSocketFrame) {
      ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
      return;
    } else
    if (!(frame instanceof TextWebSocketFrame)) {
      throw new RuntimeException("unsupported");
    }
    if (((TextWebSocketFrame) frame).text().equals("PING")) {
      ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
    } else {
      sendMsgById("a");
      TimeUnit.SECONDS.sleep(10);
      TestSendMsg.test();
    }

  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    log.info("connect to client");
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    log.info("close connect to client");
    map.remove(getAreaId(ctx));
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    log.info("connect error");
    map.remove(getAreaId(ctx));
    cause.printStackTrace();
    ctx.channel().close();
  }

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
      HandshakeComplete complete = (HandshakeComplete) evt;
      HttpHeaders headers = complete.requestHeaders();
      String areaId = headers.get("areaId");
      ctx.channel().attr(AREA_ID).setIfAbsent(Optional.ofNullable(areaId).orElse("a"));
      map.put(getAreaId(ctx), ctx.channel());
      String requestUri = complete.requestUri();
      log.info("-------------------------------------" + requestUri + "--------------------------------------");
    }
  }

  private String getAreaId(ChannelHandlerContext ctx) {
    return ctx.channel().attr(AREA_ID).get();
  }

  private String getToken(ChannelHandlerContext ctx) {
    return ctx.channel().attr(TOKEN).get();
  }

  public static void sendMsgById(String areaId) {
    Channel channel = map.get(areaId);
    if (Objects.nonNull(channel)) {
      channel.writeAndFlush(new TextWebSocketFrame(GSON.toJson("hello client...")));
    }
  }

  public static void sendMsg(String msg) {
    Channel channel = map.get("a");
    if (Objects.nonNull(channel)) {
      channel.writeAndFlush(new TextWebSocketFrame(GSON.toJson(msg)));
    }
  }

}
