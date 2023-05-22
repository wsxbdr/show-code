package tomcatDemo.Nio;

import lombok.Data;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Data
public class GPResponse {
    private OutputStream out;

    public GPResponse(OutputStream out) {
        this.out = out;
    }

    public void write(String s) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP1.1 200 OK\n")
                .append("Content-Type: text/html;\n")
                .append("\r\n")
                .append(s);
        out.write(sb.toString().getBytes(StandardCharsets.UTF_8));
    }
}
