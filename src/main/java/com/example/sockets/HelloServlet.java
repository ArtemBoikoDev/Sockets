package com.example.sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/morning")
public class HelloServlet extends HttpServlet {
    private static final String DEFAULT_NAME = "my friend";
    private String name;
    private String mood;

    public void init() {
        mood = "ok";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        getName(request);
        printMood(request, response);
    }

    private void getName(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Optional<String> sessionName = Optional.ofNullable((String) session.getAttribute("name"));
        Optional<String> requestName = Optional.ofNullable(request.getParameter("name"));

        if (!requestName.isPresent() && !sessionName.isPresent()) {
            session.setAttribute("name", DEFAULT_NAME);
        }
        if (requestName.isPresent() && !sessionName.isPresent()) {
            session.setAttribute("name", requestName.get());
        }
        name = requestName.orElseGet(() -> (String) session.getAttribute("name"));
    }

    private void printMood(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String mood = Optional.ofNullable(request.getHeader("X-Mood")).orElse(this.mood);
        PrintWriter out = response.getWriter();
        out.println("Hello, " + name + ". You are feeling: " + mood);
    }
}
