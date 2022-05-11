
import clientServer.ConnectWithServer;
import collections.CommandCollection;
import collections.HistoryCollection;
import commands.*;
import exceptions.IncorrectArgsException;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.PatternSyntaxException;

public class ClientMain {
    public static void main(String[] args) {
        CommandCollection.commandManager();
        Scanner scanner = new Scanner(System.in);
        System.out.println("For a list of commands, type help");

        while (true) {
            String command;
            String[] arguments;
            String strArgs;
            System.out.print("Please enter the command: ");
            if (!scanner.hasNext()) {
                System.out.println("Wrong input, forced shutdown");
                System.exit(0);
            }

            String input;
            while (true) {
                try {
                    input = scanner.nextLine();
                    break;
                } catch (IllegalStateException e) {
                    System.out.print("Incorrect data, please re-enter: ");
                }
            }
            input = input.trim();
            command = input.split(" ")[0];
            Result result;
            try {
                strArgs = input.replaceFirst(command, "").trim();
            } catch (PatternSyntaxException e) {
                strArgs = "";
            }
            arguments = strArgs.split(",");
            //Check if command contains in client's module
            if (CommandCollection.getClientCommands().containsKey(command)) {


                try {
                    result = (CommandCollection.getCommandColl().get(command)).function(arguments);
                } catch (NullPointerException e) {
                    continue;
                }

                for (int i = 0; i < result.getMessage().size(); i++) {
                    System.out.println(result.getMessage().get(i));
                }
                HistoryCollection.capacity(command);

            } else if (!CommandCollection.getServerCommands().containsKey(command)) {
                System.out.println("This command is not in the program, please enter the command again");
            } else {
                try {
                    arguments = ArgsValidator.argsValidator(CommandCollection.getServerCommands().get(command).getCommandArgs(), arguments);

                    DataServer dataServer = ConnectWithServer.getInstance().connectWithServer(new DataClients(command, arguments));
                    for (String s : dataServer.getMessage()) {
                        System.out.println(s);
                    }
                    HistoryCollection.capacity(command);
                } catch (IncorrectArgsException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println("Server is unreachable");
                }
            }
        }
    }
}
