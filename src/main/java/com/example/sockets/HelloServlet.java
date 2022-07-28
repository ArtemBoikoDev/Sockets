package com.example.sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/morning")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "my friend";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = Optional.ofNullable(request.getParameter("name"))
                              .orElse(message);
        PrintWriter out = response.getWriter();
        out.println("Hello, " + name);
    }
}
