package collections;

import clientServer.ConnectWithServer;
import commands.*;
import exceptions.IncorrectArgsException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.PatternSyntaxException;

/**
 * this class is using for communication with files(reading and writing)
 */
public class JavaIO {

    public static boolean readScript(String filepath) {
        Set keys = CommandCollection.getCommandColl().keySet();

        Scanner scanner;
        try {
            scanner = new Scanner(new File(filepath));
        } catch (FileNotFoundException e) {
            System.out.println("Problems with the file, try the command again");
            return false;
        }

        while (scanner.hasNext()) {
            String command;
            String[] arguments;
            String strArgs;
            String input = scanner.nextLine().trim();
            command = input.split(" ")[0];
            Result result;
            try {
                strArgs = input.replaceFirst(command, "").trim();
            } catch (PatternSyntaxException e) {
                strArgs = "";
            }
            arguments = strArgs.split(",");
            System.out.println("Command : " + command);
            //Check if command contains in client's module
            if (CommandCollection.getClientCommands().containsKey(command)) {


                result = (CommandCollection.getCommandColl().get(command)).function(arguments);

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
            } catch(IncorrectArgsException e){
                System.out.println(e.getMessage());

            } catch(IOException e){
                System.out.println("Server is unreachable");
            }
        }
    }
            return true;
}
}
