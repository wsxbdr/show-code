package tomcatDemo.Netty.servlet;

import tomcatDemo.Netty.GPRequest;
import tomcatDemo.Netty.GPResponse;

public class SecondServlet extends GpServlet {
    @Override
    public void doGet(GPRequest request, GPResponse response) throws Exception {
        doPost(request, response);
    }

    @Override
    public void doPost(GPRequest request, GPResponse response) throws Exception {
        response.write("This is secondServlet!");
    }
}
