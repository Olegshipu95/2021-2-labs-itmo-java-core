package clientServer;

import collections.CommandCollection;
import collections.HistoryCollection;
import commands.DataClients;
import commands.DataServer;
import lombok.Getter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ConnectWithServer {
    /* Порт сервера, к которому собирается
  подключиться клиентский сокет */
    private int port;
    @Getter
    private InetAddress IPAddress;
    private static ConnectWithServer instance;

    private ConnectWithServer() {
    }

    public static ConnectWithServer getInstance() {
        if (instance == null) {        //если объект еще не создан
            instance = new ConnectWithServer();    //создать новый объект
        }
        return instance;        // вернуть ранее созданный объект
    }

    public void setIPAddressAndPort(InetAddress inetAddress, int port) {
        IPAddress = inetAddress;
        this.port = port;
    }

    public DataServer connectWithServer(DataClients dataClients) throws IOException {
      /* Создайте экземпляр клиентского сокета.
Нет необходимости в привязке к определенному порту */
        DatagramSocket clientSocket = new DatagramSocket();
//        try {
//            IPAddress = InetAddress.getByName("localhost");
//        } catch (UnknownHostException e) {
//            System.out.println("UnknownHostException");
//        }

        // Создайте соответствующие буферы
        byte[] sendingDataBuffer = dataClients.getBytes();
        byte[] receivingDataBuffer = new byte[8192];
      /* Преобразуйте данные в байты
       и разместите в буферах */

        // Создайте UDP-пакет
        SocketAddress host2 = new InetSocketAddress(IPAddress,port);
        DatagramChannel datagramChannel = DatagramChannel.open();
        ByteBuffer buf ;
        buf= ByteBuffer.wrap(sendingDataBuffer);
        DatagramPacket sendingPacket = new DatagramPacket(sendingDataBuffer, sendingDataBuffer.length, IPAddress, port);

        DatagramPacket receivingPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);

        // Отправьте UDP-пакет серверу
        try {
            datagramChannel.send(buf, host2);
//            if(!datagramChannel.isConnected()){
//                System.out.println("Server is not available now");
//                return null;
//            }
//            datagramChannel.setSoTimeout(5000);
        }
        catch (IOException e) {
            System.out.println("Problems with server");
            return null;
        }
        // Получите ответ от сервера, т.е. предложение из заглавных букв
        buf.clear();
        buf = ByteBuffer.allocate(8192);
        host2 = datagramChannel.receive(buf);
        byte[] byteMessage = buf.array();
        DataServer obj;
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(byteMessage));
            obj = (DataServer) inputStream.readObject();
        } catch (ClassNotFoundException e) {
            return null;
        }
        clientSocket.close();
        return obj;
    }
}