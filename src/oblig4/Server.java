package oblig4;/*
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
            DatagramSocket socket = new DatagramSocket(5030);

            while (true) {
                // receive data from client
                byte[] buffer = new byte[256];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length); // Datagram = UDP
                socket.receive(packet);
                String received = new String(buffer, 0, packet.getLength());
                System.out.println(received);

                System.out.println("Received: " + received);

                int answer = calculate(received);

                String sendBack = received + " = " + answer;

                byte[] buffer2 = sendBack.getBytes();
                System.out.println("packet length: " + packet.getLength());
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buffer2, buffer2.length, address, port);
                socket.send(packet);

            }

        } catch (SocketException e) {
            System.out.println("Something wrong with socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static int calculate(String received) {
        received = received.replaceAll(" ", "").trim();

        char[] values = received.toCharArray();

        int op = 0;
        for (int i = 0; i < values.length ; i++) {
            if (values[i] == '+' || values[i] == '-' || values[i] == '*' || values[i] == '/') {
               op = i;
                System.out.println("op: " + op + " values.length: " + values.length);
            }
        }
        char operator = values[op];

        String sn1  = String.copyValueOf(values, 0, op);
        int lastValCount = values.length - op -1;
        String sn2 = String.copyValueOf(values, (op+1), lastValCount);

        int n1 = Integer.parseInt(sn1);
        int n2 = Integer.parseInt(sn2);

        if (operator == '+') {
            return n1 + n2;
        } else if (operator == '-') {
            return n1-n2;
        } else if (operator == '*') {
            return n1 * n2;
        } else if (operator == '/') {
            if (n2 == 0) {
                return -1;
            }
            return n1 / n2;
        }
        else {
            throw new IllegalArgumentException("Not a valid input");
        }
    }
}
