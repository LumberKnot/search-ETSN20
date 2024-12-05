import org.junit.Test;

public class TestMain {
  @Test
  public void testMain() {
    // Given
    String[] args = new String[] { "search", "pattern", "file" };
    // When
    Main.main(args);
    // Then
    // No exception is thrown
  }
}
