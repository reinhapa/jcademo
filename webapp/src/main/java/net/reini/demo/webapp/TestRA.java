package net.reini.demo.webapp;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.annotation.Resource;
import jakarta.resource.ResourceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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