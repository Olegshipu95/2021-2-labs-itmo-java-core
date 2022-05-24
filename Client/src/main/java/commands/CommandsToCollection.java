package commands;

import collections.CommandCollection;
import exceptions.IncorrectArgsException;

public abstract class CommandsToCollection extends AbstractCommand {
    @Override
    public abstract Result function(DataForArray dataForArray) ;

    public CommandsToCollection(String name, CommandArgs commandArgs, String description) {
        super(name, commandArgs, description);
        if(!(CommandCollection.getClientCommands().containsKey(name))){
            CommandCollection.getClientCommands().put(name,this);
        }
    }
    public String[] checkTypeArgs(String[] args) throws IncorrectArgsException {
        try {
            return ArgsValidator.argsValidator(getData().getCommandArgs(),args);
        } catch (IncorrectArgsException e) {
            System.out.println(e.getMessage());
            throw new IncorrectArgsException();
        }
    }
}
