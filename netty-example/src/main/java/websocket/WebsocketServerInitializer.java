package websocket;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

/**
 * @author xuben
 * @description TODO
 * @date 2023/5/25 11:57
 */
@NoArgsConstructor
public class WebsocketServerInitializer extends ChannelInitializer<SocketChannel> {

  private static final String WEBSOCKET_PATH = "/websocket";
  private static final String WEBSOCKET_PROTOCOL = "WebSocket";

  private List<ChannelHandler> handlers = new ArrayList<>();

  public WebsocketServerInitializer(ChannelHandler... handlers) {
    for (ChannelHandler handler : handlers) {
      this.handlers.add(handler);
    }
  }

  @Override
  protected void initChannel(SocketChannel channel) throws Exception {
    ChannelPipeline p = channel.pipeline();

    p.addLast(new LoggingHandler(LogLevel.INFO));
    // 因为使用http协议，所以需要使用http的编码器，解码器
    p.addLast(new HttpServerCodec());
    // 以块方式写，添加 chunkedWriter 处理器
    p.addLast(new ChunkedWriteHandler());
    /**
     * 说明：
     *  1. http数据在传输过程中是分段的，HttpObjectAggregator可以把多个段聚合起来；
     *  2. 这就是为什么当浏览器发送大量数据时，就会发出多次 http请求的原因
     */
    p.addLast(new HttpObjectAggregator(8192));
    p.addLast(new WebSocketServerCompressionHandler());
    /**
     * 说明：
     *  1. 对于 WebSocket，它的数据是以帧frame 的形式传递的；
     *  2. 可以看到 WebSocketFrame 下面有6个子类
     *  3. 浏览器发送请求时： ws://localhost:8001/websocket 表示请求的uri
     *  4. WebSocketServerProtocolHandler 核心功能是把 http协议升级为 ws 协议，保持长连接；
     *     是通过一个状态码 101 来切换的
     */
    p.addLast(new WebSocketServerProtocolHandler(WEBSOCKET_PATH, WEBSOCKET_PROTOCOL, true, 65535 * 10));
    for (ChannelHandler handler : handlers) {
      if (handler != null) {
        p.addLast(handler);
      }
    }
    p.addLast(new WebsocketMessageHandler());
  }
}
