package oblig4;

/*
Client for UDP server
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {

            InetAddress address = InetAddress.getLocalHost();
            DatagramSocket socket = new DatagramSocket();
            Scanner sc = new Scanner(System.in);
            String receivedFromServer;
            String equation;

            do {

                // Send and equation to Server (UDP connection)
                System.out.print("Write an equation: ");
                equation = sc.nextLine();

                byte[] buffer = equation.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, 0, address, 5020);
                socket.send(packet);

                // receive Answer back from Server
                byte[] receivedBuffer = new byte[50];
                packet = new DatagramPacket(receivedBuffer, 0, receivedBuffer.length);
                socket.receive(packet);
                receivedFromServer = new String(receivedBuffer,0, packet.getLength());
                System.out.println("Received from server: " + receivedFromServer);

            } while (!equation.equals(""));

        } catch (SocketTimeoutException e) {
            System.out.println("Socket timed out: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
