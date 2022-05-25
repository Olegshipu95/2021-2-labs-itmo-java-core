package connect;

import collections.CommandCollection;
import commands.DataClients;
import commands.DataForArray;
import commands.DataServer;
import commands.ServerResult;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RequestHandler implements Callable {
    DatagramPacket inputPacket;
    DatagramSocket ds;
    ExecutorService messageHandler = Executors.newFixedThreadPool(5);
    @Override
    public Object call() throws Exception {
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
                Runnable col = ()-> {

                    try {
                        new SendHandler(ds, new DataServer(message),senderAddress, senderPort).call();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                };
                messageHandler.execute(col);
                return null;
            }
            String command = obj.getCommand();
            System.out.println("Sent command from client is:" + command);
            if (!CommandCollection.getInstance().getServerCollection().containsKey(command)) {
                ArrayList<String> message = new ArrayList<>();
                System.out.println("Error!There is not command ");
                message.add("Error!There is not command " + command);
                Runnable col = ()-> {

                    try {
                        new SendHandler(ds, new DataServer(message),senderAddress, senderPort).call();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                };
                messageHandler.execute(col);
                return null;
            } else {
                if ((obj.getName() == null || obj.getPassword() == null) && !CommandCollection.getInstance().getOnlyServers().contains(command)) {
                    ArrayList<String> message = new ArrayList<>();
                    System.out.println("user hasn't logged in ");
                    message.add("user hasn't logged in ");
                    Runnable col = ()-> {
                        try {
                            new SendHandler(ds, new DataServer(message),senderAddress, senderPort).call();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    };
                    messageHandler.execute(col);
                    return null;
                }
                if (!CommandCollection.getInstance().getOnlyServers().contains(command) && !ConnectToDataBase.login(obj.getName(), obj.getPassword())) {
                    ArrayList<String> message = new ArrayList<>();
                    System.out.println("Your account is invalid");
                    message.add("Your account is invalid");
                    Runnable col = ()-> {
                        try {
                            new SendHandler(ds, new DataServer(message),senderAddress, senderPort).call();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    };
                    messageHandler.execute(col);
                    return null;
                }
                DataForArray dataForArray = new DataForArray(obj.getArgs(), obj.getName(), obj.getPassword());
                ServerResult result = (ServerResult) CommandCollection.getInstance().getServerCollection().get(command).function(dataForArray);
                if (!result.isCommand()) {
                    for (String s :
                            result.getMessage()) {
                        System.out.println(s);
                    }
                    Runnable col = ()-> {
                        try {
                            new SendHandler(ds, result.getDataServer(),senderAddress, senderPort).call();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    };
                    messageHandler.execute(col);
                    return null;
                } else {
                    Runnable col = ()-> {
                        try {
                            new SendHandler(ds, result.getDataServer(),senderAddress, senderPort).call();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    };
                    messageHandler.execute(col);
                    System.out.println("Command " + command + " has successfully done ");
                }
            }
        return null;
    }

    public RequestHandler(DatagramSocket datagramSocket, DatagramPacket datagramPacket) {
        this.ds = datagramSocket;
        this.inputPacket = datagramPacket;
    }
}
