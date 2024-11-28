package command;

import java.util.Optional;

public class CommandFactory {
  private final static String usage = "Usage: <command> <args...>\nValid commands: search";

  public static Optional<Command> getCommand(String[] args) {
    if (args.length == 0) {
      System.out.println(usage);
      return Optional.empty();
    }

    // Split arguments into command and command arguments
    String command = args[0];
    String[] commandArgs = new String[args.length - 1];
    System.arraycopy(args, 1, commandArgs, 0, commandArgs.length);

    // Create the command based on the command string
    switch (command) {
      case "search":
        return Optional.of(new Search(commandArgs));
      default:
        System.out.println("Unknown command: " + command);
        System.out.println(usage);
        return Optional.empty();
    }
  }
}
