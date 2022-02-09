package oblig3;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(5005)){

            int nrConnections = 0;

            while (true) {
                /*
                Socket socket = serverSocket.accept();
                ThreadPool pool = new ThreadPool(socket);
                pool.start();
                 */
                new ThreadPool(serverSocket.accept()).start(); // works, but less information of what is going on
                System.out.println("New connection " + nrConnections++);
            }

        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }
}
