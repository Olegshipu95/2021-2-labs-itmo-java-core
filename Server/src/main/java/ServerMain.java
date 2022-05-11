
import collections.CommandCollection;
import collections.InfoFail;
import collections.JavaIO;
import commands.DataClients;
import commands.DataServer;
import commands.Result;
import commands.ServerResult;
import commands.system.Add;
import connect.ConnectWithClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Set;
import java.util.regex.PatternSyntaxException;

public class ServerMain {
    public static final int PORT = 8080;


    public static void main(String[] args) throws IOException {
        CommandCollection.commandManager();
        InfoFail.readFile();
        JavaIO.CSVCreateObject();
        System.out.println("Server is working");
        /*
        после коннекта юзера будет
         */

            byte[] arr = new byte[8192];
            DatagramPacket outputPacket;
            int len = arr.length;
            DatagramSocket ds;
            DatagramPacket inputPacket;
            ds = new DatagramSocket(PORT);
            String outputLine;
            inputPacket = new DatagramPacket(arr, len);

            while (true) {
                System.out.println("Waiting for a client to connect...");
                ds.receive(inputPacket);
                // Выведите на экран отправленные клиентом данные
                byte[] byteMessage = inputPacket.getData();
                DataClients obj = null;
                InetAddress senderAddress = inputPacket.getAddress();
                int senderPort = inputPacket.getPort();
                try {
                    ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(byteMessage));
                    obj = (DataClients) inputStream.readObject();
                } catch (ClassNotFoundException e) {
                    System.out.println("Error!Server does not see the object ");
                    ArrayList<String> message = new ArrayList<>();
                    message.add("Error!Server does not see the object ");
                    ConnectWithClient.sendToClient(new DataServer(message), ds, senderAddress, senderPort);
                    continue;
                }
                String command = obj.getCommand();
                System.out.println("Sent command from client is:" + command);
                if (!CommandCollection.getInstance().getServerCollection().containsKey(command)) {
                    try {
                        ArrayList<String> message = new ArrayList<>();
                        System.out.println("Error!There is not command ");
                        message.add("Error!There is not command " + command);
                        ConnectWithClient.sendToClient(new DataServer(message), ds, senderAddress, senderPort);
                        continue;
                    } catch (IOException e) {
                        System.out.println("User closed the connection");
                    }
                } else {
                    String[] arguments = obj.getArgs();
                    ServerResult result =(ServerResult) CommandCollection.getInstance().getServerCollection().get(command).function(arguments);
                    if (!result.isCommand()) {
                        try {
                            for (String s:
                                 result.getMessage()) {
                                System.out.println(s);
                            }
                            ConnectWithClient.sendToClient(result.getDataServer(), ds, senderAddress, senderPort);
                            continue;
                        } catch (IOException e) {
                            System.out.println("Trouble with client");
                            continue;
                        }
                    } else {

                        try {

                            ConnectWithClient.sendToClient(result.getDataServer(), ds, senderAddress, senderPort);
                            System.out.println("Command " + command + " has successfully done ");
                        } catch (IOException ex) {
                            System.out.println("Trouble with client");
                            continue;
                        }
                    }
                }
            }
    }
}
