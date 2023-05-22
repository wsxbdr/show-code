package tomcatDemo.Netty.servlet;

import tomcatDemo.Netty.GPRequest;
import tomcatDemo.Netty.GPResponse;

public abstract class GpServlet {
    public void service(GPRequest request, GPResponse response) throws Exception {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    public abstract void doGet(GPRequest request, GPResponse response) throws Exception;

    public abstract void doPost(GPRequest request, GPResponse response) throws Exception;
}
