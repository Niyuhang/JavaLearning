package SoocketServer;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        try(
                Socket socket  = new Socket("localhost",40000);
        )
        {
            Chat chat = new Chat();
            chat.chatting(socket, "你好");
        }
    }
}
