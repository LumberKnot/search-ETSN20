package command;

import java.util.ArrayList;

public abstract class Command {
  protected String[] args;
  protected ArrayList<String> optionalArgs;
  private int minimumLength;

  public Command(String[] args, int minimumLength, String[] allowedOptionalArgs, String description)
      throws IllegalArgumentException {
    this.args = args;
    this.minimumLength = minimumLength;
    this.optionalArgs = new ArrayList<>(allowedOptionalArgs.length);

    try {
      validateArgs(args, allowedOptionalArgs);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage() + "\n" + description);
    }
  }

  protected void validateArgs(String[] args, String[] allowedOptionalArgs) throws IllegalArgumentException {
    if (args.length < minimumLength) {
      throw new IllegalArgumentException("Invalid number of arguments");
    }

    // Do not handle optional arguments if there are none
    if (args.length == minimumLength) {
      return;
    }

    // Get all the optional arguments
    String[] providedOptionalArgs = new String[args.length - minimumLength];
    System.arraycopy(args, minimumLength, providedOptionalArgs, 0, providedOptionalArgs.length);

    // Check if the provided optional arguments exist in the list of allowed
    // optional arguments
    for (String providedOptionalArg : providedOptionalArgs) {
      if (optionalArgs.contains(providedOptionalArg)) {
        throw new IllegalArgumentException("Duplicate argument: " + providedOptionalArg);
      }

      boolean found = false;
      for (String allowedOptionalArg : allowedOptionalArgs) {
        if (providedOptionalArg.equals(allowedOptionalArg)) {
          found = true;
          optionalArgs.add(providedOptionalArg);
          break;
        }
      }

      if (!found) {
        throw new IllegalArgumentException("Unknown argument: " + providedOptionalArg);
      }
    }
  }

  public abstract void execute() throws Exception;
}
