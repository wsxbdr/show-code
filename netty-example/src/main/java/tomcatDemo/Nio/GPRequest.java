package tomcatDemo.Nio;

import lombok.Data;

import java.io.InputStream;

@Data
public class GPRequest {
    private String method;
    private String url;

    public GPRequest(InputStream in) {
        try {
            String content = "";
            byte[] buff = new byte[1024];
            int len = 0;
            if ((len = in.read(buff)) != 0) {
                content = new String(buff, 0, len);
            }
            String line = content.split("\\n")[0];
            String[] arr = line.split("\\s");
            this.method = arr[0];
            this.url = arr[1].split("\\?")[0];
        } catch (Exception e) {

        }
    }
}

