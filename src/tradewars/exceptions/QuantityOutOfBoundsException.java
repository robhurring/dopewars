package tradewars.exceptions;

/**
 * Raise when the quantity exceeds the allowed space
 */
public class QuantityOutOfBoundsException extends Exception {

  public QuantityOutOfBoundsException() {
    super();
  }

  public QuantityOutOfBoundsException(String m) {
    super(m);
  }
}
