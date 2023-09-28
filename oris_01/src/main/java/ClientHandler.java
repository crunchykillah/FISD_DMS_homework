import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (InputStream is = clientSocket.getInputStream(); OutputStream os = clientSocket.getOutputStream()) {
            MainByteProtocol.send(os);
            MainByteProtocol.read(is);
            System.out.println("Сообщение получено в потоке: " + Thread.currentThread().getId());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                System.out.println("Client disconnected.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
