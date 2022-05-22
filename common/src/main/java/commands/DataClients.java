package commands;

import lombok.Getter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DataClients implements Serializable {
    @Getter
    private final String command;
    @Getter
    private final String[] args;
    @Getter
    private final String usersName;

    public DataClients(String command, String[] args, String usersName) {
        this.command = command;
        this.args = args;
        this.usersName = usersName;
    }

    public DataClients(String commandWithArgs, String[] args) {
        command = commandWithArgs;
        this.args = args;
        usersName = null;
    }



    public byte[] getBytes()  {
        byte[] serializedObj = {};
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(this);
            serializedObj = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        } catch (NullPointerException e) {
            System.out.println("Epic fail - null.");
        } catch (IOException e) {
            System.out.println("Failed to convert client message into bytes.");
        }
        return serializedObj;
    }
}
