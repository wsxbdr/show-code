package tomcatDemo.Nio.servlet;

import tomcatDemo.Nio.GPRequest;
import tomcatDemo.Nio.GPResponse;

public class SecondServlet extends GpServlet{
    @Override
    public void doGet(GPRequest request, GPResponse response) throws Exception {
        doPost(request, response);
    }

    @Override
    public void doPost(GPRequest request, GPResponse response) throws Exception {
        response.write("This is secondServlet!");
    }
}
