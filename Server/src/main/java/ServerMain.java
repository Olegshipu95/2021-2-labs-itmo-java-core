
import collections.CommandCollection;
import collections.InfoFail;
import collections.JavaIO;
import commands.*;
import connect.ConnectWithClient;
import connect.ConnectToDataBase;
import connect.RequestHandler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.*;

public class ServerMain {
    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ConnectToDataBase.connect();
        CommandCollection.commandManager();
        InfoFail.readFile();
        ExecutorService service = Executors.newFixedThreadPool(5);
        ExecutorService commandHandler = ForkJoinPool.commonPool();

        WriteTheValues.addObjectsFromDB();
        System.out.println("Server is working");
        /*
        после коннекта юзера будет
         */

            byte[] arr = new byte[8192];
            DatagramPacket outputPacket;
            int len = arr.length;
            DatagramSocket ds;
            DatagramPacket inputPacket = new DatagramPacket(arr, len);
            ds = new DatagramSocket(PORT);
            String outputLine;
            while (true) {

                System.out.println("Waiting for a client to connect...");
                DatagramPacket datagramPacket = new DatagramPacket(arr, len);
                Callable<DatagramPacket> callable = ()->{try {
                    ds.receive(datagramPacket);
                    return datagramPacket;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }};
                Future<DatagramPacket> future = service.submit(callable);
                try {
                    inputPacket = future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                DatagramPacket data = inputPacket;
                //
                Runnable col = ()-> {
                    try {
                        new RequestHandler(ds,data).call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                };
                commandHandler.execute(col);
            }
    }
}
