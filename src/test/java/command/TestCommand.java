package command;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestCommand {
  private class ExtendingCommand extends Command {
    public ExtendingCommand(String[] args) throws IllegalArgumentException {
      super(args, 2, new String[] { "-i" }, "Description");
    }

    @Override
    public void execute() {
      // Do nothing
    }
  }

  private boolean testExtendsCommand(String[] args) {
    try {
      new ExtendingCommand(args);
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  @Test
  public void testValidateArgsTrue() {
    String[] args = { "arg1", "arg2", "-i" };
    boolean result = testExtendsCommand(args);
    assertTrue(result);
  }

  @Test
  public void testValidateArgsDuplicate() {
    String[] args = { "arg1", "arg2", "-i", "-i" };
    boolean result = testExtendsCommand(args);
    assertFalse(result);
  }

  @Test
  public void testValidateArgsUnknown() {
    String[] args = { "arg1", "arg2", "-u" };
    boolean result = testExtendsCommand(args);
    assertFalse(result);
  }

  @Test
  public void testValidateArgsInvalidNumberOfArgs() {
    String[] args = { "arg1" };
    boolean result = testExtendsCommand(args);
    assertFalse(result);
  }
}
