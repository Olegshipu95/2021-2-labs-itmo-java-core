package commands;

import lombok.Getter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DataServer implements Serializable {
    @Getter
    private ArrayList<String> message;
    @Getter
    private HashSet<CommandData> commandDataHashSet;
    @Getter
    private boolean status;

    public DataServer(ArrayList<String> message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public DataServer(HashSet<CommandData> hashSet, ArrayList<String> message) {
        this.commandDataHashSet = hashSet;
        this.message = message;
    }

    public DataServer(ArrayList<String> message) {
        this.message = message;
    }

    public byte[] getBytes() {
        byte[] serializedObj = {};
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(this);
            serializedObj = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        } catch (NullPointerException e) {
            System.out.println("Epic fail - null.");
        } catch (IOException e) {
            System.out.println("Failed to convert server message into bytes.");
        }
        return serializedObj;
    }
}
