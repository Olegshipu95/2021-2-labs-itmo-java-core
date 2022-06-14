package collections;

import commands.AbstractCommand;
import commands.CommandData;
import commands.login.Login;
import commands.login.Register;
import commands.system.*;
import lombok.Getter;
import java.util.HashMap;

public class CommandCollection {
    @Getter
    private static HashMap<String, AbstractCommand> clientCommands = new HashMap<>();
    @Getter
    private static HashMap<String, CommandData> serverCommands = new HashMap<>();
    @Getter
    private static HashMap<String, AbstractCommand> loginCommands = new HashMap<>();

    public static void commandManager() {
        new Exit();
        new Help();
        new History();
        new Connect();
        new ExecuteScript();
        new Register();
        new Login();
    }


}