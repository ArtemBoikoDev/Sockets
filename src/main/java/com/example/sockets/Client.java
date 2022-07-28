package com.example.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        System.out.println("hostName = " + hostAddress);

        try (Socket clientSocket = new Socket(hostAddress, 8080)) {
            OutputStreamWriter outputWriter = new OutputStreamWriter(clientSocket.getOutputStream());
            write(hostAddress, outputWriter);
            InputStream inputStream = clientSocket.getInputStream();
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            bufferedReader.lines().forEach(System.out::println);
        }
    }

    private static void write(String hostAddress, OutputStreamWriter outputWriter) {
        PrintWriter writer = new PrintWriter(outputWriter);

        writer.println("GET /morning?name=Artem HTTP/1.1");
        writer.println("Host: " + hostAddress);
        writer.println("X-Mood: amazing");
        writer.println(); // mandatory

        writer.flush();
    }
}
