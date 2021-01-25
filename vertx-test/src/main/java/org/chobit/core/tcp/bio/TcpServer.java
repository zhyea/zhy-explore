package org.chobit.core.tcp.bio;


import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(30000);

        while (true) {
            Socket s = ss.accept();
            PrintStream ps = new PrintStream(s.getOutputStream());
            ps.println("The message come from Host.");
            ps.close();
            s.close();
        }
    }

}
