package SoocketServer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        try(
                ServerSocket ss = new ServerSocket(40000);
                Socket socket  = ss.accept();
        )
        {
                Chat chat = new Chat();
                chat.chatting(socket, null);
        }
    }
}
