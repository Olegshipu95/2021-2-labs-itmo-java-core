package connect;

import commands.DataServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class ConnectWithClient {
    public static synchronized void sendToClient (DataServer dataServer, DatagramSocket ds, InetAddress inetAddress, int port) throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(dataServer.getBytes(),dataServer.getBytes().length,inetAddress,port);
        ds.send(datagramPacket);
    }
}
