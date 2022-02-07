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
            String n1;
            String n2;
            String operator;
            String answerFromServer;

            do {
                System.out.println("\nEnter a number: ");
                n1 = sc.nextLine();
                System.out.println("Enter a new number: ");
                n2 = sc.nextLine();
                System.out.println("Enter an operator (+ or -): ");
                operator = sc.next();

                toSend.println(n1); // sends through socket inputstream to server
                toSend.println(n2);
                toSend.println(operator);

                if (!operator.equals("")) {
                    answerFromServer = receivedFromServer.readLine(); // received from server

                    System.out.println("Response from server: " + answerFromServer);
                }

            } while (!operator.equals(""));


        } catch (IOException e) {
            System.out.println("Wrong with connection: " + e.getMessage());
        }
    }
}