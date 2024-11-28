import java.util.NoSuchElementException;

import command.CommandFactory;

public class Main {
    public static void main(String[] args) {
        try {
            CommandFactory.getCommand(args).orElseThrow().execute();
        } catch (NoSuchElementException e) {
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
