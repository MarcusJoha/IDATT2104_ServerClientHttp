package oblig4;

/*
oppg 1. UDP server
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {

    public static void main(String[] args) {

        try {
            DatagramSocket socket = new DatagramSocket(5020);

            while (true) {
                // reveive data from client
                byte[] buffer = new byte[40]; // Burde ha en metode for å utvide buffer her
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length); // Datagram = UDP
                socket.receive(packet);
                String received = new String(buffer, 0, packet.getLength());
                System.out.println("Text received from client: " + new String(buffer, 0, packet.getLength()));

                // Handle data from client
                /*
                Metode for å regne ut input fra Client her.
                 */

                byte[] buffer2 = received.getBytes();
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buffer2, packet.getLength(), address, port);
                socket.send(packet);

            }

        } catch (SocketException e) {
            System.out.println("Something wron with socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
