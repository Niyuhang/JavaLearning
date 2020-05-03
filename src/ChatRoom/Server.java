package ChatRoom;

import static ChatRoom.common.Constant.*;

import ChatRoom.common.*;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Scanner;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class Server {
    private final int SERVER_PORT;
    private final ServerSocket serverSocket;
    private final Scanner sc = new Scanner(System.in);
    private ExecutorService serverPool = Executors.newCachedThreadPool();
    private Map<String, ExchangeMessage> users = new ConcurrentHashMap<>();

    // 初始化自身的参数
    public Server() throws IOException {
        SERVER_PORT = Config.PORT;
        serverSocket = new ServerSocket(SERVER_PORT);
    }

    void start() throws IOException {
        // 等待连接并且处理逻辑
        System.out.println("服务器已经启动");
        while (true) {
            Socket socket = serverSocket.accept();
            handleClient(socket);
        }
    }

    /**
     * 启动一个线程处理请求 加入到任务池
     *
     * @param socket:
     */
    private void handleClient(Socket socket) {
        serverPool.submit(new HandlerClient(socket, users));
    }

    static class HandlerClient implements Runnable {

        private final Socket socket;
        private final Map<String, ExchangeMessage> users;

        private HandlerClient(Socket socket, Map users) {
            this.socket = socket;
            this.users = users;
        }

        @Override
        public void run() {
            System.out.println("收到来自" + socket.getRemoteSocketAddress() + "的连接");
            // todo: 处理逻辑
            try {
                ExchangeMessage exchangeMessage = new ExchangeMessage(socket);
                initClient(exchangeMessage);
                while (true) {
                    // 服务端只需要不断的处理收到的请求 然后进行回应
                    Message message = exchangeMessage.receive();
                    String messageContent = message.getMessage();
                    if (message.getTo().equalsIgnoreCase(ADMIN)) {
                        // 处理发送给管理员的请求
                        System.out.println(message);
                        // todo: 处理发来的信息
                        exchangeMessage.send(handleAdminCommand(message));
                    } else {
                        // 处理发送给他人的消息
                        // todo: 进行转发
                        ExchangeMessage transformExchangeMessage = users.get(message.getTo());
                        transformExchangeMessage.send(new Message(message.getFrom(), message.getTo(), messageContent));
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private Message handleAdminCommand(Message message) {
            String messageContent = message.getMessage();
            if (messageContent.equalsIgnoreCase(GET_USERS)) {
                return new Message(ADMIN, message.getFrom(), getUsers());
            }
            // 检查用户名
            return new Message(ADMIN, message.getFrom(), USERNAME_PASS + ":" + messageContent);
        }

        /**
         * 获取当前所有用户名
         *
         * @return：
         */
        private String getUsers() {
            return users.keySet().stream().collect(Collectors.joining(";"));
        }

        private void initClient(ExchangeMessage exchangeMessage) throws IOException {
            System.out.println("初始化客户端信息");
            // 发送输入用户名信息
            exchangeMessage.send(new Message(NO_NAME, ADMIN, INIT_CLIENT_WORD));

            // 不断接受信息，然后进行判定用户名，通过后发送pass 和 欢迎语
            while (true) {
                Message message = exchangeMessage.receive();
                System.out.println("这次的消息" + message.getMessage());
                String clientUserName = message.getMessage();
                try {
                    Util.isValidUserName(clientUserName, users);
                    users.put(clientUserName, exchangeMessage);
                    exchangeMessage.send(new Message(ADMIN, clientUserName, USERNAME_PASS));
                    exchangeMessage.send(new Message(ADMIN, clientUserName, WELCOME));
                    break;
                } catch (ValueException ex) {
                    exchangeMessage.send(new Message(NO_NAME, ADMIN, "错误信息" + ex.getMessage()));
                }

            }
        }

    }

}
