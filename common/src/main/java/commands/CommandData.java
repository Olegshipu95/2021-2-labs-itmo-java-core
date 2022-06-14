package commands;

import lombok.Getter;

import java.io.Serializable;

public class CommandData implements Serializable {
    @Getter
    private final String name;
    @Getter
    private final CommandArgs commandArgs;
    @Getter
    private final String description;

    public CommandData(String name, CommandArgs commandArgs, String description) {
        this.name = name;
        this.commandArgs = commandArgs;
        this.description = description;
    }
}
