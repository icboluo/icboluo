import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

// 启动命令 javac SimpleTomcat.java  java SimpleTomcat
public class SimpleTomcat {
    // 端口号
    private int port = 8080;
    // 保存所有注册的Servlet
    private Map<String, Servlet> servletMap = new HashMap<>();

    // 注册Servlet
    public void addServlet(String url, Servlet servlet) {
        servletMap.put(url, servlet);
    }

    // 启动Tomcat
    public void start() throws IOException {
        // 创建ServerSocket监听端口
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Tomcat启动成功，端口：" + port);
        while (true) {
            // 阻塞等待客户端连接
            Socket socket = serverSocket.accept();
            // 每来一个请求，交给一个线程处理
            new Thread(() -> handleRequest(socket)).start();
        }
    }

    // 处理请求
    private void handleRequest(Socket socket) {
        try {
            // 1. 读取HTTP请求
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String requestLine = reader.readLine(); // GET /hello HTTP/1.1
            // 2. 解析请求路径
            String path = requestLine.split(" ")[1]; // /hello
            // 3. 找到对应的Servlet
            Servlet servlet = servletMap.get(path);
            Response response = new Response(socket.getOutputStream());
            if (servlet != null) {
                // 4. 调用Servlet处理请求
                servlet.service(new Request(reader), response);
            } else {
                // 5. 返回404
                response.write("HTTP/1.1 404 Not Found\r\n\r\n404 Not Found");
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        SimpleTomcat tomcat = new SimpleTomcat();
        // 注册你的Controller
        tomcat.addServlet("/hello", new HelloServlet());
        // 启动
        tomcat.start();
    }
}

// 模拟Servlet
class HelloServlet extends Servlet {
    @Override
    public void service(Request req, Response res) {
        res.write("HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\nHello World!");
    }
}

// 简化版接口
abstract class Servlet {
    public abstract void service(Request req, Response res);
}

// 简化版Request
class Request {
    private BufferedReader reader;

    public Request(BufferedReader reader) {
        this.reader = reader;
    }

    public String getParameter(String name) {
        return null;
    }
}

// 简化版Response
class Response {
    private OutputStream out;

    public Response(OutputStream out) {
        this.out = out;
    }

    public void write(String content) {
        try {
            out.write(content.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
