package ChatRoom;

import java.io.IOException;

public class ServerMainApp {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start();

    }
}
