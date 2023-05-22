package tomcatDemo.Netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpRequest;

import java.net.HttpCookie;

public class GPTomcatHandler extends SimpleChannelInboundHandler {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (msg instanceof HttpRequest) {
            System.out.println("Hello");
            HttpRequest req = (HttpRequest) msg;

            GPRequest gpRequest = new GPRequest(ctx, req);
            GPResponse gpResponse = new GPResponse(ctx, req);

            String url = gpRequest.getUrl();

            if (GPTomcat.servletMapping.containsKey(url)) {
                GPTomcat.servletMapping.get(url).service(gpRequest, gpResponse);
            } else {
                gpResponse.write("404 - NOT FOUNT");
            }
        }
    }
}
