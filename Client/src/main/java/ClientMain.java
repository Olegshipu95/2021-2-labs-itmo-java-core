
import clientServer.ConnectWithServer;
import clientServer.UsersLogin;
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
            DataForArray dataForArray;
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
            dataForArray = new DataForArray(arguments);
            //Check if command contains in client's module
            if (CommandCollection.getClientCommands().containsKey(command)) {
                try {
                    result = (CommandCollection.getClientCommands().get(command)).function(dataForArray);
                } catch (NullPointerException e) {
                    System.out.println("Null");
                    continue;
                }
                for (int i = 0; i < result.getMessage().size(); i++) {
                    System.out.println(result.getMessage().get(i));
                }
                HistoryCollection.capacity(command);

            } else if (CommandCollection.getLoginCommands().containsKey(command) && UsersLogin.getName() == null) {
                try {
                    result = (CommandCollection.getLoginCommands().get(command)).function(dataForArray);
                } catch (NullPointerException e) {
                    System.out.println("Null");
                    continue;
                }
                for (int i = 0; i < result.getMessage().size(); i++) {
                    System.out.println(result.getMessage().get(i));
                }
                HistoryCollection.capacity(command);
            } else if (CommandCollection.getLoginCommands().containsKey(command) && UsersLogin.getName() != null) {
                System.out.println("You are already logged in");
            } else if (CommandCollection.getServerCommands().containsKey(command) && UsersLogin.getName() == null) {
                System.out.println("You haven't been logged in");
            } else if (!CommandCollection.getServerCommands().containsKey(command))
                System.out.println("The command is not in the program");
            else {
                try {
                    arguments = ArgsValidator.argsValidator(CommandCollection.getServerCommands().get(command).getCommandArgs(), arguments);
                    DataServer dataServer = ConnectWithServer.getInstance().connectWithServer(new DataClients(command, arguments,UsersLogin.getName(),UsersLogin.getPassword()));
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
