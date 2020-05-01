package ChatRoom;

import java.io.IOException;

public class ClientMainApp {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.start();
    }
}
