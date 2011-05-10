package tradewars.exceptions;

/**
 * Raised on game over event
 */
public class GameOverException extends Exception {

  public GameOverException() {
    super();
  }

  public GameOverException(String m) {
    super(m);
  }
}
