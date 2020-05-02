package ChatRoom;

import ChatRoom.common.*;

import java.util.Scanner;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private final int PORT;
    private final String HOST;
    private ExchangeMessage exchangeMessage;
    private Scanner sc = new Scanner(System.in);
    // todo: 存储当前聊天的对象 和 自己的用户名

    public Client() {
        PORT = Config.PORT;
        HOST = "localhost";
    }

    void start() throws IOException {
        // 连接服务器
        System.out.println("启动客户端");
        Socket socket = new Socket(HOST, PORT);
        exchangeMessage = new ExchangeMessage(socket);
        handleClientWrite();
        handleClientRead();
    }

    /**
     * 启动写数据线程 不断处理写入
     */
    private void handleClientWrite() {
        Thread thread = new Thread(() -> {
            System.out.println("处理写入");
            while (true) {
                String message = sc.nextLine();
                exchangeMessage.send(message);
            }
        });
        thread.start();

    }

    /**
     * 启动处理读数据线程
     */
    private void handleClientRead() {
        Thread thread = new Thread(() -> {
            System.out.println("处理读取");
            while (true) {
                Message message;
                try {
                    message = exchangeMessage.receive();
                    System.out.println("收到消息" + message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }

}
