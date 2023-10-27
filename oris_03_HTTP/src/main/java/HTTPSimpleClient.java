public class HTTPSimpleClient {
    public static void main(String[] args) {
        HTTPClient client = new HTTPClient();
        client.send("itislabs.ru",80);
    }
}
