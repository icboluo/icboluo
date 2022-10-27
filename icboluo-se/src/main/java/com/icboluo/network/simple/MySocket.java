package com.icboluo.network.simple;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 客户端
 *
 * @author icboluo
 */
public class MySocket {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 10001);
        OutputStream os = socket.getOutputStream();
        os.write("taobao".getBytes());
        InputStream is = socket.getInputStream();
        int len;
        byte[] bytes = new byte[1024];
        while ((len = is.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, len));
        }
        socket.close();
    }
}
