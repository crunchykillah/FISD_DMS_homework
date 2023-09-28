import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int serverPort = 1616;

        try (Socket socket = new Socket(serverAddress, serverPort);
             OutputStream os = socket.getOutputStream();
             InputStream is = socket.getInputStream()) {
            MainByteProtocol.send(os);
            MainByteProtocol.read(is);
            MainByteProtocol.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
