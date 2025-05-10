import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UrlChat {
    private static volatile String reply = null;
    private static final Object lock = new Object();
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("服务已启动: http://localhost:8080/");
        while (true) {
            try (Socket client = server.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                 PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
                String path = in.readLine().split(" ")[1].substring(1);
                if ("favicon.ico".equals(path)) {
                    System.out.println("收到 favicon.ico 请求");
                    continue;
                }
                System.out.println("\n收到消息: " + path);
                System.out.print("请输入回复内容: ");
                synchronized (lock) {
                    reply = null;
                    new Thread(() -> {
                        reply = new Scanner(System.in).nextLine();
                        synchronized (lock) { lock.notify(); }
                    }).start();
                    lock.wait();
                }
                out.println("HTTP/1.1 200 OK");
                out.println("Content-Type: text/plain");
                out.println();
                out.println(reply);
            } catch (Exception e) {
                System.err.println("连接异常: " + e.getMessage());
            }
        }
    }
}