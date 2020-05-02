package ChatRoom;

import static ChatRoom.common.Constant.*;
import ChatRoom.common.*;

import java.util.Scanner;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private final int PORT;
    private final String HOST;
    private  String ClientName;
    private  String toName;
    private ExchangeMessage exchangeMessage;
    private Scanner sc = new Scanner(System.in);

    public Client() {
        PORT = Config.PORT;
        HOST = "localhost";
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    void start() throws IOException {
        // 连接服务器
        System.out.println("启动客户端");
        Socket socket = new Socket(HOST, PORT);
        exchangeMessage = new ExchangeMessage(socket);
        initClint();
        handleClientWrite();
        handleClientRead();
    }

    /**
     * 初始化客户端信息
     */
    private void initClint() throws IOException {
        System.out.println("初始化客户端");
        String clientName = "";
        // 获取服务端信息 并且初始化
        while (true){
            // 接收用户信息
            Message message = exchangeMessage.receive();
            String messageContent = message.getMessage();
            // 如果收到了通过的就存储并且结束初始化过程
            if(messageContent.equalsIgnoreCase(USERNAME_PASS)){
                setClientName(clientName);
                break;
            }
            // 输入用户名 并且发送给服务端
            else {
                System.out.println(messageContent);
                clientName = sc.nextLine();
                exchangeMessage.send(new Message(NO_NAME, ADMIN, clientName));
            }
        }
    }

    /**
     * 启动写数据线程 不断处理写入
     */
    private void handleClientWrite() {
        Thread thread = new Thread(() -> {
            System.out.println("处理写入");
            while (true) {
                exchangeMessage.send(createClientMessage());
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
                    System.out.println(String.format("收到消息来自%s的消息:%s", message.getFrom(), message.getMessage()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }

    /**
     * 生成客户端发送的message
     * 包含 获取发送方
     * @return
     */
    private Message createClientMessage(){
        return new Message("1", "2", "3");
    }
}
