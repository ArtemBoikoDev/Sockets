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
    private String name;
    private String mood;

    public void init() {
        name = "my friend";
        mood = "ok";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = Optional.ofNullable(request.getParameter("name")).orElse(this.name);
        String mood = Optional.ofNullable(request.getHeader("X-Mood")).orElse(this.mood);
        PrintWriter out = response.getWriter();
        out.println("Hello, " + name + ". You are feeling: " + mood);
    }
}
