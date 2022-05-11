package commands;

import collections.IdCollection;
import exceptions.IncorrectArgsException;

import java.util.Arrays;

import static commands.CommandArgs.*;

public class ArgsValidator {
    public static String[] argsValidator(CommandArgs typeOfArgs, String[] args) throws IncorrectArgsException {
        switch (typeOfArgs) {
            case NO_ARGS:
                if (!(args.length == 1 && args[0].isEmpty())) {
                    throw new IncorrectArgsException("The number of arguments must be 0");
                }
                break;
            case ID_ARGS:
                System.out.println(args.length);
                if (args.length != 1) {
                    throw new IncorrectArgsException("The number of arguments must be 1");
                } else {
                    try {
                        Integer.valueOf(args[0]);
                    } catch (NumberFormatException e) {
                        CheckTheCorrect.checkTheCorrect(Integer.valueOf(0));
                    }
                }
                break;
            case FILLING_ALL_ARGS_WITHOUT_ID:
                args = CheckTheCorrect.arrayToDesiredSize(args, 11);
                String local, local2;
                local = args[0];
                for (int i = 0; i < args.length - 1; i++) {
                    local2 = args[i + 1];
                    args[i + 1] = local;
                    local = local2;
                }
                args[0] = IdCollection.createId().toString();
                String[] argsLocal = CheckTheCorrect.fillingArgs(Arrays.copyOfRange(args, 1, args.length));
                for (int i = 1; i < argsLocal.length; i++) {
                    args[i] = argsLocal[i-1];
                }
                break;
            case FILLING_ALL_ARGS:
                args = CheckTheCorrect.arrayToDesiredSize(args, 11);
                try {
                    Integer.valueOf(args[0]);
                } catch (NumberFormatException e) {
                    System.out.print("Write Id pls: ");
                    args[0] = CheckTheCorrect.checkTheCorrect(Integer.valueOf(0)).toString();
                }
                args = CheckTheCorrect.fillingArgs(Arrays.copyOfRange(args, 1, args.length));
                break;
            case CONNECT_ARGS:
                if (args.length != 2) {
                    throw new IncorrectArgsException("The number of arguments must be 2");
                }
                try {
                    Integer.valueOf(args[1]);
                } catch (NumberFormatException e) {
                    throw new IncorrectArgsException("The second argument must be int");
                }
                break;
            case STRING:
                if (!(args.length == 1 && !args[0].isEmpty())) {
                    throw new IncorrectArgsException("You must write 1 argument with path");
                }
                break;
        }
        return args;
    }
}
