package com.example.learningjava.networking;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
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

    @SneakyThrows
    void createServer() {
        int port = 5000;
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket = serverSocket.accept();
            System.out.println("client connected");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                System.out.println("waiting for msg");
                String echo = input.readLine();
                if(StringUtils.isNotBlank(echo)) {
                    System.out.println("gor msg!");
                    out.println("message from server " + echo);
                    break;
                }
            }

            System.out.println("closing server");
            input.close();
            out.close();
            socket.close();
        }
    }

    @SneakyThrows
    void createClient(List<Consumer<PrintWriter>> messages) {
        int port = 5000;
//        localhost lub 127.0.0.1
        try (Socket socket = new Socket("localhost", port)) {

            System.out.println("client created");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            sleep(3000);

            for(var msg : messages) {
                System.out.println("sending msg");
                msg.accept(out);
                System.out.println("Received: "+input.readLine());
            }
        }
    }

    @Test
    @Disabled("not fully working ;)")
    void test1() throws InterruptedException {
        Thread serverThread = new Thread(this::createServer);

        Thread clientThread = new Thread(() -> createClient(
                Arrays.asList(
                        (writer) -> writer.write("my first message!"),
                        (writer) -> writer.write("exit")
                )));

        serverThread.start();
        clientThread.start();

        serverThread.join();
        clientThread.join();
    }
}
