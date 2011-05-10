package tradewars.exceptions;

/**
 * Raised when the user is out of space
 */
public class OutOfSpaceException extends Exception {

  public OutOfSpaceException() {
    super();
  }

  public OutOfSpaceException(String m) {
    super(m);
  }
}
