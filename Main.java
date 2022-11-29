import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Работа по протоколу TCP
 * Сервер получает байт (число)
 * и возвращает клиенту квадрат числа
 */
@SuppressWarnings("InfiniteLoopStatement")
class SqServer {

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(1020)) {
            while (true) {
                Socket socket = serverSocket.accept();
                serverClient(socket);
            }
        }
    }

    private static void serverClient(Socket socket) throws IOException {
        System.out.println("Serving client" + socket.getInetAddress());

        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        byte[] buffer = new byte[1024];
        for (int length; (length = inputStream.read(buffer)) != -1; ) {
            String str = new String(buffer, 0, length);
            System.out.println(str);
            outputStream.write(buffer, 0, length);
            outputStream.flush();
            break;
        }
    }
}