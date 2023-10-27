import java.io.*;
import java.net.Socket;

public class HTTPClient {
    public void send(String host,int port) {
        try (Socket clientSocket = new Socket(host, port);
             OutputStream os = clientSocket.getOutputStream();
             InputStream is = clientSocket.getInputStream();) {
            os.write(("POST HTTP/1.1\r\n").getBytes("UTF8"));
            os.write(("\r\n").getBytes("UTF8"));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String receivedData;
            StringBuilder responseBody = new StringBuilder();
            boolean readingBody = false;
            int contentLength = -1; // Длина контента
            while ((receivedData = br.readLine()) != null && receivedData.length() > 0) {
                System.out.println(receivedData);
                if (receivedData.startsWith("Content-Length: ")) {
                    contentLength = Integer.parseInt(receivedData.substring(16));
                    System.out.println(contentLength);
                }
            }
            if (contentLength > 0) {
                int bytesRead;
                byte[] buffer = new byte[1024];
                System.out.println((byte) is.read());
                try {
                    while ((bytesRead = is.read()) != -1) {
                        responseBody.append(new String(buffer, 0, bytesRead, "UTF8"));
                        System.out.print((byte) bytesRead + " ");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Тело ответа:\n" + responseBody.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}