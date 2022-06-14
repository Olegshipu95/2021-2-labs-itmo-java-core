
package commands.system;

import clientServer.ConnectWithServer;
import collections.ExecuteFileCollection;
import collections.JavaIO;
import commands.*;

import java.util.ArrayList;

public class ExecuteScript extends CommandsToCollection {
    public ExecuteScript() {
        super("execute_script", CommandArgs.STRING, "read and execute the script from the specified file. The script contains commands in the same form in which they are entered by the user in interactive mode.");
    }

    public Result function(DataForArray dataForArray) {
        if(ConnectWithServer.getInstance().getIPAddress()==null){
            System.out.println("You must write \"connect\" before using this command");
            return new Result(false);
        }

        String filepath = dataForArray.getArgs()[0];

        try {
            if (ExecuteFileCollection.getExecuteFileCollection().contains(filepath)) {
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add("This address has already been used, please use another one");
                return new Result(arrayList,false);
            } else {
                ExecuteFileCollection.getExecuteFileCollection().add(filepath);
                if (JavaIO.readScript(filepath)) {
                    return new Result(true);
                } else {
                    ArrayList arrayList = new ArrayList<>();
                    arrayList.add("An error occurred during the script");
                    return new Result(arrayList,false);
                }
            }
        } catch (Exception e) {
            return new Result(false);
        }
    }
}
