//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commands.system;

import collections.ExecuteFileCollection;
import collections.JavaIO;
import commands.AbstractCommand;
import commands.CommandArgs;
import commands.CommandsToCollection;
import commands.Result;

import java.util.ArrayList;

public class ExecuteScript extends CommandsToCollection {
    public ExecuteScript() {
        super("execute_script", CommandArgs.STRING, "read and execute the script from the specified file. The script contains commands in the same form in which they are entered by the user in interactive mode.");
    }

    public Result function(String ... arguments) {

        String filepath = arguments[0];

        /*try {
            if (ExecuteFileCollection.executeFileCollection.contains(filepath)) {
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add("This address has already been used, please use another one");
                return new Result(arrayList,false);
            } else {
                ExecuteFileCollection.executeFileCollection.add(filepath);
                if (JavaIO.readScript(filepath)) {
                    return new Result(true);
                } else {

                    ArrayList arrayList = new ArrayList<>();
                    arrayList.add("An error occurred during the script");
                    return new Result(arrayList,false);
                }
            }
        } catch (Exception var4) {*/
            return new Result(false);
        //}
    }
}
