package tomcatDemo.Netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import lombok.Data;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
public class GPRequest {
    private ChannelHandlerContext ctx;
    private HttpRequest req;

    public GPRequest(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }

    public String getUrl() {
        return req.uri();
    }

    public String getMethod() {
        return req.method().name();
    }

    public Map<String, List<String>> getParameters() {
        QueryStringDecoder decoder = new QueryStringDecoder(this.getUrl());
        return decoder.parameters();
    }

    public String getParameter(String name){
        Map<String, List<String>> parameters = getParameters();
        List<String> list = parameters.get(name);
        if (Objects.nonNull(list)) {
            return list.get(0);
        }
        return null;
    }
}

