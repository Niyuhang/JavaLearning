package SoocketServer;

import java.io.*;
import java.util.Scanner;
import java.net.Socket;

public class Chat {
    // 中间人 用于进行数据的接收和传输
    private Scanner sc = new Scanner(System.in);

    public void chatting(Socket from, String start) throws IOException {
        try (
                // 写入socket
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(from.getOutputStream()));
                BufferedReader reader = new BufferedReader(new InputStreamReader(from.getInputStream()));

        ) {
            // 开始聊天
            if (start != null) {
                pw.println(start);
                pw.flush();
            }
            // 接收信息
            String line;
            while (true) {
                line = reader.readLine();
                System.out.println("接收到信息" + line);
                String message = sc.nextLine();
                pw.println(message);
                pw.flush();
            }
        }
    }
}
