package command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Search extends Command {
  public Search(String[] args) throws IllegalArgumentException {
    super(args, 2, new String[] { "-i" }, "Usage: search <pattern> <file> <optional: -i>");
  }

  @Override
  public void execute() throws FileNotFoundException {
    String pattern = args[0];
    String fileName = args[1];

    File file = new File(fileName);
    Scanner scanner = new Scanner(file);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (lineContainsPattern(line, pattern)) {
        System.out.println(line);
      }
    }
    scanner.close();
  }

  private boolean lineContainsPattern(String line, String pattern) {
    if (optionalArgs.contains("-i")) {
      return line.toLowerCase().contains(pattern.toLowerCase());
    }
    return line.contains(pattern);
  }
}
