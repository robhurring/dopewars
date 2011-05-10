package tradewars.exceptions;

/**
 * Raised from trades where the user cannot afford what they want to buy
 */
public class CannotAffordException extends Exception {

  public CannotAffordException() {
    super();
  }

  public CannotAffordException(String m) {
    super(m);
  }
}
