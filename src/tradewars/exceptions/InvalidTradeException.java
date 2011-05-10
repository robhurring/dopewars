package tradewars.exceptions;

/**
 * Raised when a trade is invalid. e.g.: less than 1 quantity
 * @author rob
 */
public class InvalidTradeException extends Exception {

  public InvalidTradeException() {
    super();
  }

  public InvalidTradeException(String m) {
    super(m);
  }
}
