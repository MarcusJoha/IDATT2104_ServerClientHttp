package oblig3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadPool extends Thread {

    private Socket socket;

    public ThreadPool(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while (true) {

                String n1 = input.readLine();
                String n2 = input.readLine();
                String operator = input.readLine();

                System.out.println("Client input: " + n1 + operator + n2);

                /*
                if (n1.equals("") || n2.equals("") || operator.equals("")) {
                    break;
                }
                 */

                try {
                    Thread.sleep(3_000);
                } catch (InterruptedException e) {
                    System.out.println("Thread was interrupted: " + e.getMessage());
                }

                // Handle the data from the client
                int answer = calculate(n1, n2, operator);

                output.println(answer); // send answer back to client
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Something went wrong trying to close the socket connection");
            }
        }
    }

    public int calculate(String sn1, String sn2, String operator) {
        int n1 = Integer.parseInt(sn1);
        int n2 = Integer.parseInt(sn2);

        if (operator.equals("+")) {
            return n1 + n2;
        } else if (operator.equals("-")) {
            return n1-n2;
        }
        else {
            throw new IllegalArgumentException("Not a valid input");
        }
    }
}
