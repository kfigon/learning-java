package com.example.learningjava.networking;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

import static java.lang.Thread.sleep;

class BasicNetworkingTest {
//host - in networking - komputer
//    client-server

// transport - tcp/udp - do laczenia hostow

// port identyfikuje polaczenie. Normalnie mamy jeden kabel,
// ale wiele aplikacji - web browser, streaming etc. rozrozniane portami
// kazda aplikacja ma port zeby zroutowac ruch do odpowiedniego procesu
// komputer identyfikowany po IP

//    socket - now level networking. Socket for one point of 2 point connection

//    open connection, send request, receive response, close connection

//    code below is TCP

    void logServer(String log) {
        System.out.println("\u001B[31m" +log);
    }

    void logClient(String log) {
        System.out.println("\u001B[34m" +log);
    }

    @SneakyThrows
    void createServer() {
        int port = 5000;
//        udp - DatagramSocket()
        try(ServerSocket serverSocket = new ServerSocket(port)) {
//            we might create run code below in a new thread, so it can
//            respond to more than 1 client + timeouts on socket

            Socket socket = serverSocket.accept();
            logServer("server created");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            logServer("waiting for msg from client");
            String echo = input.readLine();
            logServer("got msg from client!");
            out.println("Hello my client, you've just sent me: " + echo);

            logServer("closing server");
            input.close();
            out.close();
            socket.close();
        }
    }

    @SneakyThrows
    void createClient(Consumer<PrintWriter> message) {
        int port = 5000;
//        localhost lub 127.0.0.1
        try (Socket socket = new Socket("localhost", port)) {

            logClient("client created");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            sleep(3000);

            logClient("sending msg to server");
            message.accept(out);
            logClient("Received from server: " + input.readLine());

            input.close();
            out.close();
        }
    }

    @Test
    void test1() throws InterruptedException {
        Thread serverThread = new Thread(this::createServer);

        Thread clientThread = new Thread(() -> createClient(
                (writer) -> writer.println("my first message!")
        ));

        serverThread.start();
        clientThread.start();

        serverThread.join();
        clientThread.join();
    }
}
