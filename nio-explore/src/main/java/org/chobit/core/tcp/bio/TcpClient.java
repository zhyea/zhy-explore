package org.chobit.core.tcp.bio;


import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {


    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 30000);
        socket.setSoTimeout(10 * 1000);

        Scanner scanner = new Scanner(socket.getInputStream());

        String line = scanner.nextLine();
        System.out.println(line);

        scanner.close();
        socket.close();
    }


}
