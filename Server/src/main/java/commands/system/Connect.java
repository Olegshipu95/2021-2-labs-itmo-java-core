package commands.system;

import collections.CommandCollection;
import commands.*;
import connect.ConnectWithClient;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
public class Connect extends CommandsToCollection {
    public Connect() {
        super("connect", CommandArgs.NO_ARGS, "resend hashmap to client with server's commands");
    }

    @Override
    public ServerResult function(String... var1) {
        HashSet<CommandData> hashSet = new HashSet<>();
        for (AbstractCommand abstractCommand:CommandCollection.getInstance().serverCollection.values()) {
            hashSet.add(abstractCommand.getData());
        }
        return new ServerResult(hashSet,true);
    }
}
