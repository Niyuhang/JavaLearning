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
        private final Map users;

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
                    exchangeMessage.send(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void initClient(ExchangeMessage exchangeMessage) throws IOException {
            System.out.println("初始化客户端信息");
            // 发送输入用户名信息
            exchangeMessage.send(new Message(NO_NAME, ADMIN, INIT_CLIENT_WORD));

            // 不断接受信息，然后进行判定用户名，通过后发送pass 和 欢迎语
            while(true){
                Message message = exchangeMessage.receive();
                System.out.println("这次的消息" + message.getMessage());
                String clientUserName = message.getMessage();
                try{
                    Util.isValidUserName(clientUserName, users);
                    exchangeMessage.send(new Message(clientUserName, ADMIN, USERNAME_PASS));
                    exchangeMessage.send(new Message(clientUserName, ADMIN, WELCOME));
                    break;
                }
                catch (ValueException ex){
                    exchangeMessage.send(new Message(NO_NAME, ADMIN, ex.toString()));
                }

            }
        }

    }

}
