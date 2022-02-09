package oblig4;

/*
oppg 1. UDP server
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {

    public static void main(String[] args) {

        try {
            DatagramSocket socket = new DatagramSocket(5010);

            while (true) {
                byte[] buffer = new byte[40]; // Burde ha en metode for å utvide buffer her
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String received = new String(buffer, 0, packet.getLength());
                System.out.println("Text received is: " + received);

                // Data is now received from Client, and we have to handle it here.


            }

        } catch (SocketException e) {
            System.out.println("Something wron with socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }


    }
}
