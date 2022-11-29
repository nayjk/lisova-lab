import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Работа по протоколу TCP
 * Клиент отправляет байт (число) серверу,
 * затем получает квадрат числа
 */
class SqClient {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 1020)) {
            int i = 0;
            Scanner scanner = new Scanner(System.in);
            String n = scanner.nextLine();
            while (i < 1) {
                i++;
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(n.getBytes());
                outputStream.flush();

                InputStream inputStream = socket.getInputStream();

                byte[] buffer = new byte[1024];
                for (int length; (length = inputStream.read(buffer)) != -1; ) {
                    String str = new String(buffer, 0, length);
                    System.out.println("Response: " + str);
                    break;


                }
            }
        }
    }
}