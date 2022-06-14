package connect;

import commands.DataServer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class SendHandler implements Callable {
    DatagramSocket ds;
    DataServer dataServer;
    InetAddress inetAddress;
    int port;

    public SendHandler(DatagramSocket ds, DataServer dataServer, InetAddress inetAddress, int port) {
        this.ds = ds;
        this.dataServer = dataServer;
        this.inetAddress = inetAddress;
        this.port = port;
    }

    @Override
    public Object call() throws Exception {
        ConnectWithClient.sendToClient(dataServer, ds, inetAddress, port);
        return null;
    }
}
