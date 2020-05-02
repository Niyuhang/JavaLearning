package ChatRoom;

import static ChatRoom.common.Constant.*;

import ChatRoom.common.*;

import java.util.Scanner;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private final int PORT;
    private final String HOST;
    private String ClientName;
    private String toName = ""; // 避免出现NullPointError
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
        initClient();
        handleClientWrite();
        handleClientRead();
    }

    /**
     * 初始化客户端信息
     */
    private void initClient() throws IOException {
        System.out.println("初始化客户端");
        String clientName = "";
        // 获取服务端信息 并且初始化
        while (true) {
            // 接收用户信息
            Message message = exchangeMessage.receive();
            String messageContent = message.getMessage();
            // 如果收到了通过的就存储并且结束初始化过程
            if (messageContent.equalsIgnoreCase(USERNAME_PASS)) {
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
            while (true) {
                // 处理异常情况
                try {
                    exchangeMessage.send(createClientMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                    exchangeMessage.close();
                    System.exit(-3);
                }
            }
        });
        thread.start();

    }

    /**
     * 启动处理读数据线程
     */
    private void handleClientRead() {
        Thread thread = new Thread(() -> {
            while (true) {
                Message message;
                try {
                    message = exchangeMessage.receive();
                    // 处理服务端发送的信息
                    String messageContent = message.getMessage();
                    if (message.getFrom().equalsIgnoreCase(ADMIN)) {
                        handleServerData(messageContent);
                    } else {
                        System.out.println(String.format("收到消息来自%s的消息:%s", message.getFrom(), message.getMessage()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    exchangeMessage.close();
                    System.exit(-3);
                }
            }
        });
        thread.start();
    }

    private void handleServerData(String messageContent) {
        // 如果收到了用户就可以开始后面的信息
        if (messageContent.startsWith(USERNAME_PASS)) {
            this.toName = messageContent.substring(USERNAME_PASS.length() + 1);
            System.out.println("请开始聊天吧");
            synchronized (HOST) {
                // 和主线程通信
                HOST.notify();
            }
        }
        else if (messageContent.equalsIgnoreCase(WELCOME)){
            System.out.println(messageContent);
        }
        // 没通过就重新开始输入
        else {
            System.out.println(messageContent);
            System.out.println("请重新输入用户名");
            synchronized (HOST) {
                // 和主线程通信
                HOST.notify();
            }
        }


    }

    /**
     * 生成客户端发送的message
     * 包含 获取发送方
     *
     * @return
     */
    private Message createClientMessage() throws IOException, InterruptedException {
        // 读取用户输入
        while (true) {
            String messageData = sc.nextLine();
            // 分析得到toName
            // 如果重新输入了@就更新 toName
            if (messageData.startsWith(CONNECT_WITH_CLIENT_PATTERN)) {
                String[] messages = messageData.split("\\s+");
                String toName = messages[0].substring(1);
                // 发送给服务端 验证是否是有效的用户名
                exchangeMessage.send(new Message(ClientName, ADMIN, toName));
                synchronized (HOST) {
                    // 等待读取线程处理
                    HOST.wait();
                }
            }
            // 直接和用户联系
            else if (!toName.equalsIgnoreCase("")) {
                return new Message(ClientName, toName, messageData);
            }
            // 既没有toName 又失败了 就继续输入
            else {
                System.out.println("请继续输入你要聊天的用户");
            }
        }

    }
}
