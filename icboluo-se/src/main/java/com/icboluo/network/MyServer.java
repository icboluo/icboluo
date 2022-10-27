package com.icboluo.network;

import lombok.SneakyThrows;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author icboluo
 * @since 2022-10-27 12:49
 */
public class MyServer {
    private static ServerSocket serverSocket;
    private static Socket socket;

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("service start");
        serverSocket = new ServerSocket(10001);
        while (true) {
            socket = serverSocket.accept();
            new Thread(new Listener()).start();
            new Thread(new Send()).start();
        }
    }

    static class Listener implements Runnable {
        @Override
        @SneakyThrows
        public void run() {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            while (true) {
                System.out.println(ois.readObject());
            }
        }
    }

    static class Send implements Runnable {
        @Override
        @SneakyThrows
        public void run() {
            try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {
                Scanner sc = new Scanner(System.in);
                while (true) {
                    System.out.println("请输入要发送的内容");
                    String str = sc.nextLine();
                    Map<String, String> map = new HashMap<>();
                    map.put("type", "chat");
                    map.put("msg", str);
                    oos.writeObject(map);
                    oos.flush();
                }
            }
        }
    }
}
