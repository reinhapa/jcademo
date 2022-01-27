package net.reini.demo.webapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.resource.ResourceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.reini.jcademo.SampleConnection;
import net.reini.jcademo.SampleConnectionFactory;

@WebServlet("/TestRA")
public class TestRA extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Resource(mappedName = "java:/eis/Sample")
    private SampleConnectionFactory connectionFactory;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String result = null;
        try (SampleConnection connection = connectionFactory.getConnection()) {
            result = connection.helloWorld();
        } catch (ResourceException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.println(result);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}