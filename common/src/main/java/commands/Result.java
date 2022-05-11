package commands;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;

public class Result {
    @Getter
    private ArrayList<String> message;
    @Getter
    private boolean command;
    @Getter
    private HashSet<CommandData> hashSet;
    public Result(ArrayList<String> message, boolean command) {
        this.message = message;
        this.command = command;
    }

    public Result(boolean command) {
        if (message == null) {
            if (command) {
                message = new ArrayList<>();
                message.add("The command has already done");
            } else {
                message = new ArrayList<>();
                message.add("Error in command");
            }
        }
        this.command=command;
    }
    public Result(HashSet<CommandData> hashSet,boolean command){this.message = message;
     this.hashSet = hashSet;
     this.command = command;
        if (message == null) {
            if (command) {
                message = new ArrayList<>();
                message.add("The command has already done");
            } else {
                message = new ArrayList<>();
                message.add("Error in command");
            }
        }
    }
}
