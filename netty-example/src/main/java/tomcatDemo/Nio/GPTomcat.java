package tomcatDemo.Nio;

import tomcatDemo.Nio.servlet.GpServlet;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class GPTomcat {

    private int port = 8080;
    private ServerSocket server;
    private Map<String, GpServlet> servletMapping =  new HashMap<>();

    private Properties webXml = new Properties();

    private void init(){
        try{
            String WEB_INF = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(WEB_INF + "web.properties");

            webXml.load(fis);

            for (Object k : webXml.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webXml.getProperty(key);
                    String className = webXml.getProperty(servletName + ".className");

                    GpServlet servlet = (GpServlet) Class.forName(className).newInstance();
                    servletMapping.put(url, servlet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        init();

        try{
            server = new ServerSocket(this.port);
            System.out.println("GPTomcat已启动，监听的端口是： " + this.port);

            while (true){
                Socket client = server.accept();
                process(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void process(Socket client) throws Exception {
        InputStream in = client.getInputStream();
        OutputStream out = client.getOutputStream();

        GPRequest request = new GPRequest(in);
        GPResponse response = new GPResponse(out);

        String url = request.getUrl();

        if (servletMapping.containsKey(url)) {
            servletMapping.get(url).service(request, response);
        } else {
            response.write("404 - Not Found");
        }

        out.flush();
        out.close();

        in.close();
        client.close();
    }

    public static void main(String[] args) {
        new GPTomcat().start();
    }
}
