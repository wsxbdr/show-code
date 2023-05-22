package tomcatDemo.Nio.servlet;

import tomcatDemo.Nio.GPRequest;
import tomcatDemo.Nio.GPResponse;

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
