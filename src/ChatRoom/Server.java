package ChatRoom;
import ChatRoom.common.*;

import java.util.Scanner;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Server {
    private int SERVER_PORT;
    private final ServerSocket serverSocket;
    private final Scanner sc = new Scanner(System.in);
    private ExecutorService serverPool = Executors.newCachedThreadPool();
    // TODO: 存储用户名

    // 初始化自身的参数
    public Server() throws IOException {
        this.SERVER_PORT = Config.PORT;
        this.serverSocket = new ServerSocket(this.SERVER_PORT);
    }

    public void start() throws IOException {
        // 等待连接并且处理逻辑
        System.out.println("服务器已经启动");
        Socket socket = serverSocket.accept();
        handleClient(socket);
    }
        // 启动一个线程处理请求

    /**
     * 处理连接 加入到任务池
     * @param socket
     */
    private void handleClient(Socket socket){
        serverPool.submit(new HandlerClient(socket));
    }


    static class HandlerClient implements Runnable {

        private final Socket socket;

        private HandlerClient(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("收到来自"+ socket.getRemoteSocketAddress() + "的连接");
            // todo: 处理逻辑
        }
    }

}
