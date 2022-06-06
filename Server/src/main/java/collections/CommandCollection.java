package collections;

import commands.AbstractCommand;
import commands.onlyServers.Connect;
import commands.onlyServers.Login;
import commands.onlyServers.Register;
import commands.onlyServers.Save;
import commands.system.*;
import lombok.Getter;
import lombok.extern.java.Log;

import java.util.HashMap;
import java.util.HashSet;

public class CommandCollection {
    private static CommandCollection instance;
    private CommandCollection(){}
    public static CommandCollection getInstance(){
        if(instance == null){		//если объект еще не создан
            instance = new CommandCollection();	//создать новый объект
        }
        return instance;		// вернуть ранее созданный объект
    }
    @Getter
    private HashMap<String, AbstractCommand> serverCollection = new HashMap();
    @Getter
    HashSet<String> onlyServers = new HashSet<>();

    public static void commandManager() {
        new Connect();
        new Add();
        new AverageOfMinutesOfWaiting();
        new Clear();
        new Info();
        new PrintAscending();
        new RemoveById();
        new RemoveAt();
        new RemoveLower();
        new Save();
        new Show();
        new Register();
        new Login();
        new SumOfMinutesOfWaiting();
        new UpdateId();
    }

}