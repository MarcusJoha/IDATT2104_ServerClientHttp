import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 5005)) {

            BufferedReader receivedFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter toSend = new PrintWriter(socket.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);
            String answerFromServer;

            String line;

            do {

                System.out.print("\nEnter a number: ");
                line = sc.nextLine(); // hopper over denne av en eller annen grunn
                toSend.println(line); // sends through socket inputstream to server

                System.out.print("Enter a new number: ");
                line = sc.nextLine();
                toSend.println(line);

                System.out.print("Enter an operator (+ or -): ");
                line = sc.nextLine();
                toSend.println(line);

                if (!line.equals("")) {
                    answerFromServer = receivedFromServer.readLine(); // received from server
                    System.out.println("Response from server: " + answerFromServer);
                }

            } while (!line.equals(""));


        } catch (IOException e) {
            System.out.println("Wrong with connection: " + e.getMessage());
        }
    }
}