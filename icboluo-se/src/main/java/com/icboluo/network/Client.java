package com.icboluo.network;

import com.icboluo.util.SimpleThreadUtil;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author icboluo
 * @since 2022-10-27 12:39
 */
public class Client {
    private static Socket socket;
    private static ObjectOutputStream oos;

    private static boolean status;

    public static void main(String[] args) {
        System.out.println("client start");
        while (!status) {
            connect();
            SimpleThreadUtil.sleep(3000L);
        }
    }

    private static void connect() {
        try {
            // 不要对socket进行资源关闭，这个不需要关
            socket = new Socket("127.0.0.1", 10001);
            status = true;
            oos = new ObjectOutputStream(socket.getOutputStream());
            new Thread(new Listener()).start();
            new Thread(new Send()).start();
            new Thread(new Heart()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void reConnect() {
        while (!status) {
            System.out.println("正在重新连接");
            connect();
            SimpleThreadUtil.sleep(3000L);
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

    static class Heart implements Runnable {

        @Override
        public void run() {
            Scanner sc = new Scanner(System.in);
            try {
                while (true) {
                    SimpleThreadUtil.sleep(5000L);
                    Map<String, String> map = new HashMap<>();
                    map.put("type", "heart");
                    map.put("msg", "心跳包");
                    oos.writeObject(map);
                    oos.flush();
                }
            } catch (IOException e) {
                status = false;
                reConnect();
            }
        }
    }
}
