package collections;

import commands.Command;
import commands.CommandData;
import commands.system.*;
import lombok.Getter;

import java.util.HashMap;

public class CommandCollection {
    @Getter
    private static HashMap<String, Command> commandColl = new HashMap<>();
    @Getter
    private static HashMap<String, String> clientCommands = new HashMap<>();
    @Getter
    private static HashMap<String, CommandData> serverCommands = new HashMap<>();

    public static void commandManager() {
        new Exit();
        new Help();
        new History();
        new Connect();
        new ExecuteScript();
    }


}