package oblig3.HTTP_Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServerMain {

    public static void main(String[] args) {

        int portnumber = 8008;

        try {

            ServerSocket serverSocket = new ServerSocket(portnumber);

            while (true) {

                Socket socket = serverSocket.accept();
                System.err.println("New connection!!");

                BufferedReader br = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );

                String reader;
/*
                while ((reader = br.readLine()) != null) {
                    System.out.println(reader);
                    if (reader.isEmpty()) break;
                }

 */
                OutputStream output = socket.getOutputStream();
                // HTML code
                output.write("HTTP/1.1 200 OK\r\n".getBytes());
                output.write("Content-Type: text/html;charset=utf-8\r\n".getBytes());
                output.write("\r\n".getBytes());
                output.write("<html>".getBytes());
                output.write("<body>".getBytes());
                output.write("<h1>Vær pilset, du har koblet deg opp til min enkle web-tjener</h1>".getBytes());
                output.write("<h3>Dette er min header</h3>".getBytes());
                output.write("<ul>".getBytes());

                while ((reader = br.readLine()) != null) {
                    output.write("<li>".getBytes());
                    String line = reader;
                    output.write(line.getBytes());
                    output.write("</li>".getBytes());
                    if (reader.isEmpty()) break;
                }
                output.write("</ul>".getBytes());
                output.write("</body>".getBytes());
                output.write("</html>".getBytes());
                output.flush();
                System.err.println("oblig4.Client connection is closed");

                br.close();
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("wrong with connection: " + e.getMessage());
        }
    }
}
