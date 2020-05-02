package ChatRoom.common;

import java.net.Socket;
import java.io.*;

public class ExchangeMessage {
    private final Socket socket;
    private BufferedReader reader;
    private PrintWriter fw;

    public ExchangeMessage(Socket socket) throws IOException {
        this.socket = socket;
        initIO(socket);
    }

    /**
     * 初始化Input 和 Output
     *
     * @param socket:
     */
    private void initIO(Socket socket) throws IOException {
        fw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public Message receive() throws IOException {
        String line;
        while (true) {
            line = reader.readLine();
            if (line != null && line.length() > 0) {
                break;
            }
        }
        return Message.generateMessage(line);
    }

    /**
     * 发送信息
     * @param message
     */
    public void send(Message message) {
        // 利用println 而不是利用write
        fw.println(message.toString());
        fw.flush();
    }


    /**
     * 关闭当前数据中转服务
     */
    public void close() {
        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
